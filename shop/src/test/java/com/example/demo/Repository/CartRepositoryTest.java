package com.example.demo.Repository;

import com.example.demo.DTO.MemberDTO;
import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager entityManager;

    public Member createMember(){
        MemberDTO memberDTO = MemberDTO.builder()
                .name("hi")
                .address("seoul")
                .password("1234")
                .email("hi@gamil.com")
                .build();
        return Member.createMember(memberDTO, passwordEncoder);
    }

    @Test
    @DisplayName("CartRepository Test")
    public void findCartTest(){
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = Cart.builder()
                .member(member)
                .build();
        cartRepository.save(cart);

        entityManager.flush();
        entityManager.clear();

        Cart savedCart = cartRepository.findById(cart.getId()).get();
        assertEquals(savedCart.getMember().getId(), member.getId());

        if(savedCart.getMember().equals(member))
            System.out.println("Same");
        else
            System.out.println("Wrong : " + savedCart.getMember() + " " + member);
    }
}