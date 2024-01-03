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

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class JpaBankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @Test
    @DisplayName("사용 가능한 모든 은행코드 조회 테스트")
    void allBankSelectByUsable() {
        List<Bank> banks = bankRepository.findBySTCD(DataStcd.STCD_USE);
        assertThat(banks.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("은행 코드로 조회 테스트")
    void selectByBankCode() {
        Bank bank = bankRepository.findByBnkCdAndSTCD(321L, DataStcd.STCD_USE);
        assertThat(bank).isNull();
    }

}
