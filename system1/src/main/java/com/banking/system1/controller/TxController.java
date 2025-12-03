
package com.banking.system1.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.banking.system1.dto.TransactionDTO;
import com.banking.system1.service.RoutingService;

@RestController
@RequestMapping("/system1")
public class TxController {

    @Autowired
    private RoutingService routing;

    @PostMapping("/transaction")
    public Object process(@RequestBody TransactionDTO tx) {
        if (tx.getCardNumber() == null || tx.getPin() == null || tx.getAmount() <= 0 || tx.getType() == null)
            return java.util.Map.of("success", false, "message", "Missing fields");

        if (!tx.getType().equalsIgnoreCase("withdraw") && !tx.getType().equalsIgnoreCase("topup"))
            return java.util.Map.of("success", false, "message", "Invalid type");

        if (!tx.getCardNumber().startsWith("4"))
            return java.util.Map.of("success", false, "message", "Card range not supported");

        return routing.route(tx);
    }
}
