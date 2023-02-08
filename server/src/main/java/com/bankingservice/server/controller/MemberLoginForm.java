package com.bankingservice.server.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberLoginForm {

    private String id;
    private String pw;

}
