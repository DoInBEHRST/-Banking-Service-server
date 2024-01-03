package com.bankingservice.server.bank.repository;

import com.bankingservice.server.bank.entity.Bank;
import com.bankingservice.server.common.constants.DataStcd;
import java.util.List;

public interface BankRepository {

    List<Bank> findBySTCD(DataStcd stcd);

    Bank findByBnkCdAndSTCD(Long bnkCd, DataStcd stcd);

}
