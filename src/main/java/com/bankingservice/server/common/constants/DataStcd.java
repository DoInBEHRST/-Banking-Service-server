package com.bankingservice.server.common.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DataStcd {


    // 사용
    STCD_USE("01"),

    // 삭제
    STCD_NOT_USE("02");

    private String statusCode;
}
