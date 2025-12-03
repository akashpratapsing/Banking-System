package com.banking.system1;

import com.banking.system1.dto.ResponseDTO;
import com.banking.system1.dto.TransactionDTO;
import com.banking.system1.service.RoutingService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class System1Tests {

    @MockBean
    WebClient.Builder builder;

    @Autowired
    RoutingService routingService;

    @Test
    void testUnsupportedCardRange() {
        TransactionDTO dto = new TransactionDTO(
                "5111111111111111",   // card
                "1234",               // pin
                100.0,                // amount
                "topup"               // type
        );

        ResponseDTO res = routingService.route(dto);

        assertNotNull(res);
        assertFalse(res.isSuccess());
        assertEquals("Card range not supported", res.getMessage());
    }
}



