package com.bankingservice.server.bank.DTO;

import com.bankingservice.server.bank.entity.Bank;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankCodeAndName {

    @NotBlank
    private Long bncCd;

    @NotBlank
    private String bnkNm;

    public BankCodeAndName(Bank bank) {
        this.bncCd = bank.getBnkCd();
        this.bnkNm = bank.getBNK_NM();
    }
}
