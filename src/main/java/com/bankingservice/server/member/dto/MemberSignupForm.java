package com.bankingservice.server.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberSignupForm {

    @NotBlank
    private String id;

    @NotBlank
    private String pw;

    private String regNum;

    @NotBlank
    private boolean isPrt;

    private String prtId;

}
