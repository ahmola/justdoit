package com.example.demo.DTO;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class MemberDTO {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String address;
}
