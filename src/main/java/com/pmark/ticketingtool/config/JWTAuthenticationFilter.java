package com.pmark.ticketingtool.config;


import com.pmark.ticketingtool.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


import static com.pmark.ticketingtool.config.SecurityConst.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager aut){
        authenticationManager = aut;

        setFilterProcessesUrl(AUTH_URL);

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        User user = new User();
        user.setUser(request.getParameter("username"));
        user.setPass(request.getParameter("password"));


        return authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getUser(), user.getPass()));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


        byte[] signingKey = SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .setHeaderParam("typ",TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject((String)authResult.getPrincipal())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).compact();


        response.addHeader(HEADER, TOKEN_PREFIX + token);
        response.getWriter().write(token);
    }

}
