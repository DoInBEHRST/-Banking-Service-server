package com.bankingservice.server.member.service;

import static com.bankingservice.server.common.constants.DataStcd.STCD_NOT_USE;

import com.bankingservice.server.common.constants.DataStcd;
import com.bankingservice.server.member.dto.MemberLoginForm;
import com.bankingservice.server.member.dto.MemberSignupForm;
import com.bankingservice.server.member.dto.UserInfoDTO;
import com.bankingservice.server.member.entity.Member;
import com.bankingservice.server.member.repository.JpaMemberRepository;
import com.bankingservice.server.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(JpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public UserInfoDTO login(String id, String pw) {
        Member member = memberRepository.findByIDAndPW(id, pw);
        if (member == null) {
            throw new IllegalStateException("아이디 혹은 비밀번호가 다릅니다.");
        }

        // 사용여부 체크
        member.checkUsable();

        Member prtMember = memberRepository.findByMBNO(member.getPRT_MBNO());

        UserInfoDTO loginMember = UserInfoDTO.builder()
            .id(member.getID())
            .nm(member.getNM())
            .prtId(prtMember != null ? prtMember.getID() : null)
            .pnt(member.getPNT())
            .build();

        return loginMember;
    }

    public UserInfoDTO signup(MemberSignupForm memberSignupForm) {

        Member idCheckMember = memberRepository.findByID(memberSignupForm.getId());
        if (idCheckMember != null) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }

        String prtId = memberSignupForm.getPrtId();
        Member parents = null;
        if (prtId != null) {
            parents = memberRepository.findByID(prtId);
            if (parents == null) {
                throw new IllegalStateException("잘못된 부모 아이디 입니다.");
            }
        }

        Member newMember = memberRepository.save(
            Member.builder()
                .ID(memberSignupForm.getId())
                .PW(memberSignupForm.getPw())
                .STCD(DataStcd.STCD_USE)
                .PRT_MBNO(parents != null ? parents.getMBNO() : null)
                .PNT(0L)
                .build()
        );

        UserInfoDTO newMemberInfo = UserInfoDTO.builder()
            .id(newMember.getID())
            .nm(newMember.getNM())
            .prtId(parents != null ? parents.getID() : null)
            .pnt(newMember.getPNT())
            .build();

        return newMemberInfo;
    }

    public boolean withdrawal(MemberLoginForm memberLoginForm) {

        String id = memberLoginForm.getId();
        String pw = memberLoginForm.getPw();

        Member deleteMember = memberRepository.findByIDAndPW(id, pw);
        if (deleteMember == null) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }

        deleteMember.setSTCD(STCD_NOT_USE);
        memberRepository.save(deleteMember);

        return true;
    }
}
