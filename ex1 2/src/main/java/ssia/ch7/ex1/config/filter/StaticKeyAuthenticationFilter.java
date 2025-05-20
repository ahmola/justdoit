package ssia.ch7.ex1.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class StaticKeyAuthenticationFilter implements Filter {
    @Value("${authorization.key}")
    private String authorizationKey;


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        var HttpRequest = (HttpServletRequest) request;
        var HttpResponse = (HttpServletResponse) response;

        String authentication = HttpRequest.getHeader("Authorization");

        if(authorizationKey.equals(authentication)){
            chain.doFilter(request,response);
        }else {
            HttpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
