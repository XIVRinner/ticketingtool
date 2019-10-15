package com.pmark.ticketingtool.config;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.pmark.ticketingtool.config.SecurityConst.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER);

        UsernamePasswordAuthenticationToken user = getAuthentication(request);

        if (user == null) {
            response.sendError(401, "This user is not authorized!");
        }
        else{
            SecurityContextHolder.getContext().setAuthentication(user);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if(StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)){
            Jws<Claims> parsedToken;
            try {
                byte[] key = SECRET.getBytes();

                parsedToken = Jwts.parser().setSigningKey(SECRET)
                        .parseClaimsJws(token.replace("Bearer", ""));


                String username = parsedToken.getBody().getSubject();


                if(StringUtils.isNotEmpty(username))
                    return new UsernamePasswordAuthenticationToken(username, null);

            }catch (ExpiredJwtException ex){
                log.error("Expired Token: ", ex);

            }catch(Exception ex){
                log.error("Authentication exception caught : ", ex);
                return null;
            }
        }

        return null;

    }
}
