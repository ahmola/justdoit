package com.example.demo.config.AuthenticationProvider;

import com.example.demo.config.AuthenticationServerProxy;
import com.example.demo.config.Authentication.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());

        boolean result = proxy.sendOTP(username, code);

        if(result){
            return new OtpAuthentication(username, code);
        }else {
            throw new BadCredentialsException("bad Credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }
}
