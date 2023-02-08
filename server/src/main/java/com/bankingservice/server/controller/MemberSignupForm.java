package com.bankingservice.server.controller;

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

    private String id;

    private String pw;

    private String regNum;

    private boolean isPrt;

    private String prtId;

}
