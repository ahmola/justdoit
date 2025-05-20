package ssia.ch10.ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import ssia.ch10.ex2.repository.CustomCsrfTokenRepository;

@Configuration
public class ProjectConfiguration {

    @Bean
    public CsrfTokenRepository customCsrfTokenRepository(){
        return new CustomCsrfTokenRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(
                c->{
                    c.csrfTokenRepository(customCsrfTokenRepository());
                }
        )
        ;

        http.authorizeHttpRequests()
                .anyRequest().permitAll();

        return http.build();
    }
}
