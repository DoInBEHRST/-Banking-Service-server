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

        Member newMamber = Member.builder()
            .ID("권은비")
            .PW("1234")
            .build();

        Member result = memberRepository.save(newMamber);

        assertThat(result).isSameAs(newMamber);

    }

    @Test
    void ID_검색_테스트() {

        String id = "권은비";

        Member newMamber = Member.builder()
            .ID(id)
            .PW("1234")
            .build();

        Member newMamber2 = Member.builder()
            .ID("사쿠라")
            .PW("1234")
            .build();

        Member savedMember = memberRepository.save(newMamber);
        memberRepository.save(newMamber2);

        Member findMember = memberRepository.findByID(id);

        assertThat(newMamber).isSameAs(findMember);

    }

    @Test
    void ID_PW_검색_테스트() {

        String id = "권은비";
        String pw = "1234";

        Member newMamber = Member.builder()
            .ID(id)
            .PW(pw)
            .build();

        Member newMamber2 = Member.builder()
            .ID("사쿠라")
            .PW("1234")
            .build();

        Member savedMember = memberRepository.save(newMamber);
        memberRepository.save(newMamber2);

        Member findMember = memberRepository.findByIDAndPW(id, pw);

        assertThat(newMamber).isSameAs(findMember);

    }

    @Test
    void MBNO_검색_테스트() {

        Long mbno = 0L;

        Member newMamber = Member.builder()
            .ID("권은비")
            .PW("1234")
            .build();

        Member newMamber2 = Member.builder()
            .ID("사쿠라")
            .PW("1234")
            .build();

        Member savedMember = memberRepository.save(newMamber);
        memberRepository.save(newMamber2);
        Member findMember = memberRepository.findByMBNO(mbno);

        assertThat(newMamber.getID()).isEqualTo("권은비");
    }


}
