package com.example.demo.Entity;

import com.example.demo.DTO.MemberDTO;
import com.example.demo.Service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class MemberTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Save Test")
    public void saveMember(){
        MemberDTO memberDTO = MemberDTO.builder()
                .name("hi")
                .email("email@gmail.com")
                .password("1234")
                .address("seoul")
                .build();
        Member member = Member.createMember(memberDTO, passwordEncoder);
        memberService.saveMember(member);
    }
}