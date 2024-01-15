package com.bankingservice.server.bank.DTO;

import com.bankingservice.server.bank.entity.Bank;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsableBanks {

    private List<BankCodeAndName> banks;

    public UsableBanks(List<Bank> data) {

        banks = new ArrayList<>();

        data.forEach((bank ->
            banks.add(new BankCodeAndName(bank)))
        );
    }

    public int getSize() {
        return banks.size();
    }
}
