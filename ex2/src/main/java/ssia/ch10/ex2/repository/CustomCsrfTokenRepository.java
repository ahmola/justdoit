package ssia.ch10.ex2.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import ssia.ch10.ex2.model.Token;

import java.util.Optional;
import java.util.UUID;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {
    @Autowired
    private JpaTokenRepository jpaTokenRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if(existingToken.isPresent()){
            Token token1 = existingToken.get();
            token1.setToken(token.getToken());
        }else {
            Token token1 = new Token();
            token1.setToken(token.getToken());
            token1.setIdentifier(identifier);
            jpaTokenRepository.save(token1);
        }

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if(existingToken.isPresent()){
            Token token = existingToken.get();
            return new DefaultCsrfToken(
                    "X-CSRF-TOKEN",
                    "_csrf",
                    token.getToken());
        }

        return null;
    }
}