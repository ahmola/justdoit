package com.example.jms.config;

import com.example.jms.service.CustomUserDetailService;
import jakarta.jms.JMSException;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableJms
@EnableWebSecurity
public class ProjectConfig {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    public ActiveMQConnectionFactory artemisConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(artemisConnectionFactory());
        return jmsTemplate;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider)
                .userDetailsService(customUserDetailService);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .authenticationManager(authenticationManager(http))
        ;

        http
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().authenticated())
        ;

        http
                .formLogin(Customizer.withDefaults())
                .httpBasic()
        ;

        return http.build();
    }
}
