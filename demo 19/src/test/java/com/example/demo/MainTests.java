package com.example.demo;

import com.example.demo.configs.CustomSecurityContextFactory;
import com.example.demo.configs.WithCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloUnauthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "korsica")
    public void helloAuthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello! korsica"))
                .andExpect(status().isOk());
    }

    @Test
    public void helloAuthenticatedWithUser() throws Exception {
        mockMvc.perform(
                        get("/hello").with(user("chai")))
                .andExpect(content().string("Hello! chai"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("chai")
    public void helloUserAuthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }


}
