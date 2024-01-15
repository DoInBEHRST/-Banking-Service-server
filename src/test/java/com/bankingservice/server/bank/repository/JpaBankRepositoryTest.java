package com.bankingservice.server.bank.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.bankingservice.server.bank.entity.Bank;
import com.bankingservice.server.common.constants.DataStcd;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@Transactional
public class JpaBankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @Test
    @DisplayName("사용 가능한 모든 은행코드 조회 테스트")
    void allBankSelectByUsable() {

        Bank kbBank = Bank.builder()
            .bnkCd(1L)
            .BNK_NM("국민은행")
            .STCD(DataStcd.STCD_USE)
            .build();

        Bank bnkBank = Bank.builder()
            .bnkCd(2L)
            .BNK_NM("부산은행")
            .STCD(DataStcd.STCD_NOT_USE)
            .build();

        bankRepository.save(kbBank);
        bankRepository.save(bnkBank);

        List<Bank> banks = bankRepository.findBySTCD(DataStcd.STCD_USE);
        assertThat(banks).doesNotContain(bnkBank);
    }

    @Test
    @DisplayName("은행 코드로 조회 테스트")
    void selectByBankCode() {

        Bank kbBank = Bank.builder()
            .bnkCd(1L)
            .BNK_NM("국민은행")
            .STCD(DataStcd.STCD_USE)
            .build();

        Bank bnkBank = Bank.builder()
            .bnkCd(2L)
            .BNK_NM("부산은행")
            .STCD(DataStcd.STCD_NOT_USE)
            .build();

        bankRepository.save(kbBank);
        bankRepository.save(bnkBank);

        Bank bank = bankRepository.findByBnkCdAndSTCD(1L, DataStcd.STCD_USE);
        assertThat(bank.getBNK_NM()).isEqualTo(kbBank.getBNK_NM());
    }

}
