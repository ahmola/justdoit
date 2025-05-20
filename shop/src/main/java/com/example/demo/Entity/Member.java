package com.example.demo.Entity;

import com.example.demo.Base.BaseEntity;
import com.example.demo.Constant.Role;
import com.example.demo.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter@ToString
@Entity
@Table
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        return Member.builder()
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .address(memberDTO.getAddress())
                .role(Role.USER)
                .build();
    }
}