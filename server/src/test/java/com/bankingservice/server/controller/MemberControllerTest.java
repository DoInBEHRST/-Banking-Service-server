package com.bankingservice.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bankingservice.server.dto.MemberLoginForm;
import com.bankingservice.server.dto.MemberSignupForm;
import com.bankingservice.server.dto.ResponseErrorMessage;
import com.bankingservice.server.dto.UserInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void 로그인_실패_테스트() throws Exception {

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

    @Test
    void 회원가입_테스트() throws Exception {

        String body = mapper.writeValueAsString(
            MemberSignupForm.builder()
                .id("권은비")
                .pw("1234")
                .regNum("1234-1234")
                .isPrt(false)
                .build()
        );

        mvc.perform(
                post("/signup")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(
                status().isOk()
            )
            .andExpect(
                content().json(
                    mapper.writeValueAsString(
                        UserInfoDTO.builder()
                            .id("권은비")
                            .build()
                    )
                )
            );

    }

    @Test
    void 로그인_성공_테스트() throws Exception {

        String id = "권은비";
        String pw = "1234";

        String newMemberInfo = mapper.writeValueAsString(
            MemberSignupForm.builder()
                .id(id)
                .pw(pw)
                .regNum("1234-1234")
                .isPrt(false)
                .build()
        );

        mvc.perform(
                post("/signup")
                    .content(newMemberInfo)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(
                status().isOk()
            )
            .andExpect(
                content().json(
                    mapper.writeValueAsString(
                        UserInfoDTO.builder()
                            .id(id)
                            .build()
                    )
                )
            );

        String loginInfo = mapper.writeValueAsString(
            MemberLoginForm.builder()
                .id(id)
                .pw(pw)
                .build()
        );

        mvc.perform(
                post("/login")
                    .content(loginInfo)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(
                status().isOk()
            )
            .andExpect(
                content().json(
                    mapper.writeValueAsString(
                        UserInfoDTO.builder()
                            .id(id)
                            .build()
                    )
                )
            );
    }

    @Test
    void 회원탈퇴_테스트() throws Exception {
        String id = "권은비";
        String pw = "1234";

        String newMemberInfo = mapper.writeValueAsString(
            MemberSignupForm.builder()
                .id(id)
                .pw(pw)
                .regNum("1234-1234")
                .isPrt(false)
                .build()
        );

        String loginInfo = mapper.writeValueAsString(
            MemberLoginForm.builder()
                .id(id)
                .pw(pw)
                .build()
        );

        mvc.perform(
            post("/signup")
                .content(newMemberInfo)
                .contentType(MediaType.APPLICATION_JSON)
        );

        mvc.perform(
                put("/withdrawal")
                    .content(loginInfo)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(
                status().isOk()
            );

        mvc.perform(
                post("/login")
                    .content(loginInfo)
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
                            .message("존재하지 않는 아이디 입니다.")
                            .build()
                    )
                )
            );


    }
}
