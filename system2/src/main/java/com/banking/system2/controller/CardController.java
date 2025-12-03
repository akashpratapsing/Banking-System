
package com.banking.system2.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.banking.system2.service.CardService;
import com.banking.system2.dto.TransactionDTO;
import com.banking.system2.dto.ResponseDTO;

@RestController
@RequestMapping("/system2")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping("/process")
    public ResponseDTO process(@RequestBody TransactionDTO tx) {
        return service.process(tx);
    }
}
