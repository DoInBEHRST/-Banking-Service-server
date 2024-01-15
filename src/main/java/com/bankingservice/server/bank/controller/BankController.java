package com.bankingservice.server.bank.controller;

import com.bankingservice.server.bank.DTO.BankCodeAndName;
import com.bankingservice.server.bank.DTO.UsableBanks;
import com.bankingservice.server.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/banks")
    public ResponseEntity<UsableBanks> getAllUsableBanks() {
        return ResponseEntity.ok(bankService.getAllUsableBanks());
    }

    @PostMapping("/bank")
    public ResponseEntity<BankCodeAndName> addBank(@RequestBody BankCodeAndName bankCodeAndName) {
        return ResponseEntity.ok(bankService.saveBank(bankCodeAndName));
    }
}
