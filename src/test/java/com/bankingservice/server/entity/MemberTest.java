package com.bankingservice.server.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bankingservice.server.constants.MemberStcd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @Test
    @DisplayName("Member 의 사용여부가 NOT_USE 이면 예외가 발생한다")
    void checkIsUsable() {

        Member member = Member.builder()
            .ID("권은비")
            .PW("1234")
            .STCD(MemberStcd.STCD_NOT_USE)
            .build();

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> member.checkUsable());

        assertThat(e.getMessage()).isEqualTo("존재하지 않는 아이디 입니다.");
    }

}
