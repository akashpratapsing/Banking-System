
package com.example.system1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.example.system1.dto.TransactionDTO;
import com.example.system1.dto.ResponseDTO;

@Service
public class RoutingService {

    private final WebClient client = WebClient.create("http://localhost:8082/system2");

    public ResponseDTO route(TransactionDTO tx) {

        // Card range check
        if (!tx.getCardNumber().startsWith("4")) {
            return new ResponseDTO(false, "Card range not supported", null);
        }

        try {
            return client.post()
                    .uri("/process")
                    .bodyValue(tx)
                    .retrieve()
                    .bodyToMono(ResponseDTO.class)
                    .block();

        } catch (WebClientResponseException e) {
            // When System2 throws an error (like 400/500)
            return new ResponseDTO(false, "System2 error: " + e.getMessage(), null);

        } catch (Exception e) {
            return new ResponseDTO(false, "Error contacting System2", null);
        }
    }
}

