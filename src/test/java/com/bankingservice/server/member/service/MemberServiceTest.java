package com.bankingservice.server.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.bankingservice.server.member.constants.MemberStcd;
import com.bankingservice.server.member.dto.MemberLoginForm;
import com.bankingservice.server.member.dto.MemberSignupForm;
import com.bankingservice.server.member.dto.UserInfoDTO;
import com.bankingservice.server.member.entity.Member;
import com.bankingservice.server.member.repository.JpaMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    JpaMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 없는_사람_로그인() {

        String id = "권은비";
        String pw = "1234";

        when(memberRepository.findByIDAndPW(anyString(), anyString())).thenReturn(null);

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.login(id, pw)
        );

        assertThat(e.getMessage()).isEqualTo("아이디 혹은 비밀번호가 다릅니다.");

    }

    @Test
    void 회원가입_테스트() {

        MemberSignupForm memberInfo = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(false)
            .prtId(null)
            .build();

        Member member = Member.builder()
            .ID("권은비")
            .PW("1234")
            .REG_NUM("1234-1234")
            .STCD(MemberStcd.STCD_USE)
            .PNT(0L)
            .build();

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        UserInfoDTO newMember = memberService.signup(memberInfo);

        assertThat(newMember.getId()).isEqualTo("권은비");

    }

    @Test
    void 아이디_중복_테스트() {
        MemberSignupForm memberInfo = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(false)
            .prtId(null)
            .build();

        MemberSignupForm memberInfo2 = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(false)
            .prtId(null)
            .build();

        Member member = Member.builder()
            .ID("권은비")
            .PW("1234")
            .REG_NUM("1234-1234")
            .PRT_MBNO(null)
            .PNT(0L)
            .build();

        when(memberRepository.findByID(any())).thenReturn(null);
        when(memberRepository.save(any())).thenReturn(member);

        memberService.signup(memberInfo);

        when(memberRepository.findByID(any())).thenReturn(member);

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.signup(memberInfo2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디 입니다.");

    }

    @Test
    void 부모아이디_유효성_테스트() {
        MemberSignupForm member = MemberSignupForm.builder()
            .id("권은비")
            .pw("1234")
            .regNum("1234-1234")
            .isPrt(true)
            .prtId("사쿠라")
            .build();

        when(memberRepository.findByID("권은비")).thenReturn(null);
        when(memberRepository.findByID("사쿠라")).thenReturn(null);

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.signup(member));

        assertThat(e.getMessage()).isEqualTo("잘못된 부모 아이디 입니다.");

    }

    @Test
    void 회원탈퇴_테스트() {

        String id = "권은비";
        String pw = "1234";

        MemberSignupForm memberInfo = MemberSignupForm.builder()
            .id(id)
            .pw(pw)
            .regNum("1234-1234")
            .isPrt(true)
            .prtId(null)
            .build();

        MemberLoginForm memberLoginForm = MemberLoginForm.builder()
            .id(id)
            .pw(pw)
            .build();

        Member member = Member.builder()
            .ID(id)
            .PW(pw)
            .REG_NUM("1234-1234")
            .STCD(MemberStcd.STCD_USE)
            .PNT(0L)
            .build();

        // 회원 저장
        when(memberRepository.save(any())).thenReturn(member);
        memberService.signup(memberInfo);

        // 회원 저장 후 조회 시
        when(memberRepository.findByIDAndPW(id, pw)).thenReturn(member);

        // 회원 탈퇴
        memberService.withdrawal(memberLoginForm);

        member.setSTCD(MemberStcd.STCD_NOT_USE);

        // 회원 로그인 시
        when(memberRepository.findByIDAndPW(id, pw)).thenReturn(member);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.login(memberInfo.getId(), memberInfo.getPw()));

        assertThat(e.getMessage()).isEqualTo("존재하지 않는 아이디 입니다.");


    }

}
