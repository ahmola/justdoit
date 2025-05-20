package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void helloAuthenticatingWithValidUser() throws Exception{
        mvc.perform(
                get("/hello")
                        .with(httpBasic("chai", "1234"))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void helloAuthenticatingWithInvalidUser() throws Exception{
        mvc.perform(
                get("/hello")
                        .with(httpBasic("korsica", "12345"))
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginWithWrongAuthority() throws Exception{
        mvc.perform(
                formLogin()
                        .user("chai").password("1234")
        )
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound())
                .andExpect(authenticated());
    }

    @Test
    public void loginWithCorrectAuthority() throws Exception{
        mvc.perform(
                formLogin()
                        .user("chai").password("1234")
        )
                .andExpect(redirectedUrl("/hello"))
                .andExpect(status().isFound())
                .andExpect(authenticated());
    }
}
