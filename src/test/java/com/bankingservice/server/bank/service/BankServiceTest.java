package com.bankingservice.server.bank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bankingservice.server.bank.DTO.BankCodeAndName;
import com.bankingservice.server.bank.DTO.UsableBanks;
import com.bankingservice.server.bank.entity.Bank;
import com.bankingservice.server.bank.repository.JpaBankRepository;
import com.bankingservice.server.common.constants.DataStcd;
import jakarta.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @InjectMocks
    BankService bankService;

    @Mock
    JpaBankRepository bankRepository;

    @BeforeEach
    public void beforeEach() {
        bankService = new BankService(bankRepository);
    }

    @Test
    @DisplayName("은행 추가 테스트")
    void validateSaveBank() {
        BankCodeAndName newBank = new BankCodeAndName(1L, "국민은행");

        Bank bank = Bank.builder()
            .bnkCd(newBank.getBncCd())
            .BNK_NM(newBank.getBnkNm())
            .build();

        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        BankCodeAndName result = bankService.saveBank(newBank);
        assertThat(result).usingRecursiveComparison().isEqualTo(newBank);
    }

    @Test
    @DisplayName("은행 추가시 BNK_CD 가 중복되면 예외가 발생한다.")
    void exceptionDuplicationBnkCd() {

        BankCodeAndName bank = new BankCodeAndName(1L, "국민은행");

        when(bankRepository.save(any(Bank.class))).thenThrow(new PersistenceException());

        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> bankService.saveBank(bank)
        );

        assertThat(e.getMessage()).isEqualTo("은행 정보 추가에 실패했습니다.");
    }

    @Test
    @DisplayName("사용가능한 은행 목록 조회")
    void validateByUsableBanks() {

        when(bankRepository.findBySTCD(DataStcd.STCD_USE)).thenReturn(getMockBanks());

        UsableBanks usableBanks = bankService.getAllUsableBanks();

        assertThat(usableBanks)
            .usingRecursiveComparison()
            .isEqualTo(new UsableBanks(getMockBanks()));
    }

    private List<Bank> getMockBanks() {

        List<Bank> banks = new ArrayList<>();

        banks.add(
            Bank.builder()
                .bnkCd(1L)
                .STCD(DataStcd.STCD_USE)
                .BNK_NM("국민은행")
                .build()
        );

        banks.add(
            Bank.builder()
                .bnkCd(2L)
                .STCD(DataStcd.STCD_USE)
                .BNK_NM("신한은행")
                .build()
        );

        return banks;
    }
}
