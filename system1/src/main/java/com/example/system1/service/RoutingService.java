
package com.example.system1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.example.system1.dto.TransactionDTO;
import com.example.system1.dto.ResponseDTO;

@Service
public class RoutingService {

    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8082/system2")
            .defaultHeaders(headers -> {
                String basicAuth = "Basic " +
                        java.util.Base64.getEncoder().encodeToString("admin:admin123".getBytes());
                headers.add("Authorization", basicAuth);
            })
            .build();

    public ResponseDTO route(TransactionDTO tx) {

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
        }
        catch (Exception e) {
            return new ResponseDTO(false, "System2 error: " + e.getMessage(), null);
        }
    }
}


