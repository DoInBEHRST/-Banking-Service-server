package com.bankingservice.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bankingservice.server.dto.MemberLoginForm;
import com.bankingservice.server.dto.ResponseErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void login_테스트() throws Exception {

        String id = "권은비";
        String pw = "1234";

        String body = mapper.writeValueAsString(
            MemberLoginForm.builder()
                .id(id)
                .pw(pw)
                .build()
        );

        mvc.perform(
                post("/login")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(
                status().isUnauthorized()
            )
            .andExpect(
                content().json(
                    mapper.writeValueAsString(
                        ResponseErrorMessage.builder()
                            .code(HttpStatus.UNAUTHORIZED.value())
                            .message("아이디 혹은 비밀번호가 다릅니다.")
                            .build()
                    )
                )
            );


    }
}
