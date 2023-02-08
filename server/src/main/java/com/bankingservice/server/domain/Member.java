package com.bankingservice.server.domain;

import com.bankingservice.server.constants.MemberStcd;
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
    private Enum<MemberStcd> stcd;
    private String id;
    private String pw;
    private String regNum;
    private String prtId;
    private long pnt;
    private String lstMdfMbno;
    private SimpleDateFormat lstMdfDatetime;


}
