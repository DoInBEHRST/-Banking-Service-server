package com.bankingservice.server.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.bankingservice.server.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class JpaMemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 맴버_저장_테스트() {

        Member newMember = Member.builder()
            .ID("권은비")
            .PW("1234")
            .build();

        Member result = memberRepository.save(newMember);

        assertThat(result).isSameAs(newMember);

    }

    @Test
    void ID_검색_테스트() {

        String id = "권은비";

        Member newMember = Member.builder()
            .ID(id)
            .PW("1234")
            .build();

        Member newMember2 = Member.builder()
            .ID("사쿠라")
            .PW("1234")
            .build();

        memberRepository.save(newMember);
        memberRepository.save(newMember2);

        Member findMember = memberRepository.findByID(id);

        assertThat(newMember).isSameAs(findMember);

    }

    @Test
    void ID_PW_검색_테스트() {

        String id = "권은비";
        String pw = "1234";

        Member newMember = Member.builder()
            .ID(id)
            .PW(pw)
            .build();

        Member newMember2 = Member.builder()
            .ID("사쿠라")
            .PW("1234")
            .build();

        memberRepository.save(newMember);
        memberRepository.save(newMember2);

        Member findMember = memberRepository.findByIDAndPW(id, pw);

        assertThat(newMember).isSameAs(findMember);

    }

    @Test
    void MBNO_검색_테스트() {

        Long mbno = 0L;

        Member newMember = Member.builder()
            .ID("권은비")
            .PW("1234")
            .build();

        Member newMember2 = Member.builder()
            .ID("사쿠라")
            .PW("1234")
            .build();

        memberRepository.save(newMember);
        memberRepository.save(newMember2);
        memberRepository.findByMBNO(mbno);

        assertThat(newMember.getID()).isEqualTo("권은비");
    }


}
