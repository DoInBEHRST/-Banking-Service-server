package com.bankingservice.server.domain;

import java.text.SimpleDateFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private long mbno;
    private String stcd;
    private String id;
    private String pw;
    private String regNum;
    private long prtMbno;
    private long pnt;
    private SimpleDateFormat lstMdfMbno;
    private SimpleDateFormat lstMdfDatetime;
}
