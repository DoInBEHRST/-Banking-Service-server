package com.bankingservice.server.bank.repository;

import com.bankingservice.server.bank.entity.Bank;
import com.bankingservice.server.common.constants.DataStcd;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface JpaBankRepository extends Repository<Bank, Long>, BankRepository {

    @Override
    Bank save(Bank bank);

    @Override
    List<Bank> findBySTCD(DataStcd stcd);

    @Override
    Bank findByBnkCdAndSTCD(Long bnkCd, DataStcd stcd);
}
