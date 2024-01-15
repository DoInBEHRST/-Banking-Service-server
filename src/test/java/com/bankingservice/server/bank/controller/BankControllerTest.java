package com.bankingservice.server.bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bankingservice.server.bank.DTO.BankCodeAndName;
import com.bankingservice.server.bank.DTO.UsableBanks;
import com.bankingservice.server.bank.entity.Bank;
import com.bankingservice.server.common.dto.ResponseErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BankControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("은행 정보 추가 API 테스트")
    void validateAddBank() throws Exception {

        BankCodeAndName kbBank = new BankCodeAndName(1L, "국민은행");

        String requestBody = mapper.writeValueAsString(
            kbBank
        );

        mvc.perform(
                post("/bank")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                content().json(
                    mapper.writeValueAsString(kbBank)
                ));

    }

    @Test
    @DisplayName("은행 정보 추가 시 BNK_CD 가 중복되면 500 에러가 발생한다.")
    void exceptionDuplicationBnkCd() throws Exception {

        BankCodeAndName kbBank = new BankCodeAndName(1L, "국민은행");

        String requestBody = mapper.writeValueAsString(
            kbBank
        );

        BankCodeAndName bnkBank = new BankCodeAndName(1L, "부산은행");

        String secondRequestBody = mapper.writeValueAsString(
            bnkBank
        );

        mvc.perform(
                post("/bank")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(
                content().json(
                    mapper.writeValueAsString(kbBank)
                ));

        mvc.perform(
                post("/bank")
                    .content(secondRequestBody)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().is5xxServerError())
            .andExpect(
                content().json(
                    mapper.writeValueAsString(
                        ResponseErrorMessage.builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("은행 정보 추가에 실패했습니다.")
                            .build()
                    )
                )
            );

    }

    @Test
    @DisplayName("사용가능한 은행 목록 조회 API 테스트")
    void validateByApiOfSearchAllUsableBanks() throws Exception {

        BankCodeAndName kbBank = new BankCodeAndName(1L, "국민은행");

        String requestBody = mapper.writeValueAsString(
            kbBank
        );

        mvc.perform(
                post("/bank")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(
                content().json(
                    mapper.writeValueAsString(kbBank)
                ));

        mvc.perform(
                get("/banks")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(
                status().isOk()
            )
            .andExpect(
                content().json(
                    mapper.writeValueAsString(
                        getMockData()
                    )
                )
            );

    }

    private UsableBanks getMockData() {
        List<Bank> banks = new ArrayList<>();

        banks.add(Bank.builder().bnkCd(1L).BNK_NM("국민은행").build());

        return new UsableBanks(banks);
    }

}
