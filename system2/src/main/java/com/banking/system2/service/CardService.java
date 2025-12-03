
package com.banking.system2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.banking.system2.repository.CardRepository;
import com.banking.system2.entity.Card;
import com.banking.system2.dto.TransactionDTO;
import com.banking.system2.dto.ResponseDTO;
import com.banking.system2.util.HashUtil;

@Service
public class CardService {

    @Autowired
    private CardRepository repo;

    public ResponseDTO process(TransactionDTO tx) {
        Card card = repo.findById(tx.getCardNumber()).orElse(null);
        if (card == null) return ResponseDTO.fail("Invalid card");

        if (!card.getPinHash().equals(HashUtil.sha256(tx.getPin()))) return ResponseDTO.fail("Invalid PIN");

        if ("withdraw".equalsIgnoreCase(tx.getType())) {
            if (card.getBalance() < tx.getAmount()) return ResponseDTO.fail("Insufficient Balance");
            card.setBalance(card.getBalance() - tx.getAmount());
        } else if ("topup".equalsIgnoreCase(tx.getType())) {
            card.setBalance(card.getBalance() + tx.getAmount());
        } else {
            return ResponseDTO.fail("Invalid transaction type");
        }

        repo.save(card);
        return ResponseDTO.ok("Success", card.getBalance());
    }
}
