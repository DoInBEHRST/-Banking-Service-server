package com.bankingservice.server.domain;

import java.text.SimpleDateFormat;
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
