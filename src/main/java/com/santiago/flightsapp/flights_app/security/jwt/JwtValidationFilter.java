package com.santiago.flightsapp.flights_app.security.jwt;

import static com.santiago.flightsapp.flights_app.security.jwt.JwtConstants.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiago.flightsapp.flights_app.security.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationFilter.class);

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        // If no Authorization header or doesn't start with prefix -> continue the chain
        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "").trim();

        // 1) TRY only token parsing/validation — catch JwtException specifically
        try {
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (JwtException | IllegalArgumentException ex) {
            // token invalid / expired / malformed
            logger.warn("Invalid JWT: {}", ex.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(CONTENT_TYPE);
            HashMap<String,String> body = new HashMap<>();
            body.put("error", "Invalid JWT");
            body.put("message", ex.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            return;
        } catch (Exception ex) {
            // unexpected parsing error (rare) — log and return 401
            logger.error("Unexpected error while validating JWT", ex);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(CONTENT_TYPE);
            HashMap<String,String> body = new HashMap<>();
            body.put("error", "JWT validation error");
            body.put("message", ex.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            return;
        }

        // 2) If token ok (authentication set), continue filter chain.
        //    DO NOT catch general exceptions here — let them bubble to the global handler.
        chain.doFilter(request, response);
    }
}
