package com.bankingservice.server;

import com.bankingservice.server.repository.InmemoryMemberRepository;
import com.bankingservice.server.repository.MemberRepository;
import com.bankingservice.server.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new InmemoryMemberRepository();
    }
}
