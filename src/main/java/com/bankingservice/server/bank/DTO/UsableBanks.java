package com.bankingservice.server.bank.DTO;

import com.bankingservice.server.bank.entity.Bank;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsableBanks {

    private final List<BankCodeAndName> banks = new ArrayList<>();

    public UsableBanks(List<Bank> data) {
        data.forEach((bank ->
            banks.add(new BankCodeAndName(bank)))
        );
    }
}
