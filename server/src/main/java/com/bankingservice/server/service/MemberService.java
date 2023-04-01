package com.bankingservice.server.service;

import static com.bankingservice.server.constants.MemberStcd.STCD_NOT_USE;

import com.bankingservice.server.constants.MemberStcd;
import com.bankingservice.server.dto.MemberSignupForm;
import com.bankingservice.server.dto.UserInfoDTO;
import com.bankingservice.server.entity.Member;
import com.bankingservice.server.repository.JpaMemberRepository;
import com.bankingservice.server.repository.MemberRepository;
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
        if (member.getSTCD().equals(STCD_NOT_USE)) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }

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
                .STCD(MemberStcd.STCD_USE)
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

    public boolean withdrawal(String id) {
        Member deleteMember = memberRepository.findByID(id);
        if (deleteMember == null) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }

        deleteMember.setSTCD(STCD_NOT_USE);
        memberRepository.save(deleteMember);

        return true;
    }
}
