package ssia.ch6.ex1.service;

import org.hibernate.procedure.ParameterMisuseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ssia.ch6.ex1.constant.EncryptionAlgorithm;
import ssia.ch6.ex1.model.CustomUserDetails;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);

        return checkPassword(userDetails, password, userDetails.getUser().getAlgorithm());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


    private Authentication checkPassword(CustomUserDetails user, String rawPassword, EncryptionAlgorithm algorithm){
        switch (algorithm){
            case BCRYPT :
                if(bCryptPasswordEncoder.matches(rawPassword, bCryptPasswordEncoder.encode(user.getPassword()))){   // this should be changed!!!
                    return new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            user.getAuthorities());
                }else {
                    throw new BadCredentialsException("Bad credentials");
                }
            case SCRYPT :
                if(sCryptPasswordEncoder.matches(rawPassword, sCryptPasswordEncoder.encode(user.getPassword()))){   // this should be changed!!!
                    return new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            user.getAuthorities());
                }else {
                    throw new BadCredentialsException("Bad credentials");
                }
        }
        throw new ParameterMisuseException("Wrong Algorithm!");
    }
}