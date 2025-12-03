package com.banking.system2;

import com.banking.system2.dto.TransactionDTO;
import com.banking.system2.entity.Card;
import com.banking.system2.repository.CardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class System2Tests {

    @Autowired MockMvc mockMvc;
    @Autowired CardRepository repo;
    @Autowired ObjectMapper mapper;

    @BeforeEach
    void setup() {
        repo.deleteAll();
        // PIN = 1234 → SHA-256 hash
        repo.save(new Card(
                "4111111111111111",
                "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
                500.0
        ));
    }

    @Test
    void testValidTopup() throws Exception {
        TransactionDTO dto = new TransactionDTO(
                "4111111111111111",
                "1234",
                100.0,
                "topup"
        );

        mockMvc.perform(post("/system2/process")
                        .content(mapper.writeValueAsString(dto))
                        .contentType("application/json")
                        .header("Authorization", "Basic YWRtaW46YWRtaW4xMjM="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.balance").value(600.0));
    }

    @Test
    void testInvalidPin() throws Exception {
        TransactionDTO dto = new TransactionDTO(
                "4111111111111111",
                "9999",
                50.0,
                "withdraw"
        );

        mockMvc.perform(post("/system2/process")
                        .content(mapper.writeValueAsString(dto))
                        .contentType("application/json")
                        .header("Authorization", "Basic YWRtaW46YWRtaW4xMjM="))
                .andExpect(status().isOk())   // backend always returns 200
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid PIN"));
    }

    @Test
    void testInsufficientBalance() throws Exception {
        TransactionDTO dto = new TransactionDTO(
                "4111111111111111",
                "1234",
                2000.0,
                "withdraw"
        );

        mockMvc.perform(post("/system2/process")
                        .content(mapper.writeValueAsString(dto))
                        .contentType("application/json")
                        .header("Authorization", "Basic YWRtaW46YWRtaW4xMjM="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Insufficient Balance"));
    }

    @Test
    void testCardNotFound() throws Exception {
        TransactionDTO dto = new TransactionDTO(
                "4999999999999999",
                "1234",
                50.0,
                "topup"
        );

        mockMvc.perform(post("/system2/process")
                        .content(mapper.writeValueAsString(dto))
                        .contentType("application/json")
                        .header("Authorization", "Basic YWRtaW46YWRtaW4xMjM="))
                .andExpect(status().isOk())  // NOT 400
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid card"));
    }
}
