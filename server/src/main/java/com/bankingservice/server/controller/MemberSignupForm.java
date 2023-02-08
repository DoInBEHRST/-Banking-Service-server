package com.bankingservice.server.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupForm {

    private String id;

    private String pw;

    private String regNum;

    private boolean isPrt;

    private String prtId;

}
