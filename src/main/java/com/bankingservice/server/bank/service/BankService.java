package com.bankingservice.server.bank.service;

import com.bankingservice.server.bank.DTO.BankCodeAndName;
import com.bankingservice.server.bank.DTO.UsableBanks;
import com.bankingservice.server.bank.entity.Bank;
import com.bankingservice.server.bank.repository.BankRepository;
import com.bankingservice.server.common.constants.DataStcd;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public BankCodeAndName saveBank(BankCodeAndName newBank) {
        Bank bank = Bank.builder()
            .bnkCd(newBank.getBncCd())
            .BNK_NM(newBank.getBnkNm())
            .STCD(DataStcd.STCD_USE)
            .build();

        if (bankRepository.existsByBnkCd(bank.getBnkCd())) {
            throw new IllegalArgumentException("은행 정보 추가에 실패했습니다.");
        }

        bankRepository.save(bank);

        return newBank;
    }

    public UsableBanks getAllUsableBanks() {
        List<Bank> totalBanks = bankRepository.findBySTCD(DataStcd.STCD_USE);
        return new UsableBanks(totalBanks);
    }
}
