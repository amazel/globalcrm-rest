package com.globalcrm.rest.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    final private UserDetailsService userDetailsService;
    final private JwtTokenUtil jwtTokenUtil;
    final private String authorizationHeader;

    public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, String authorizationHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authorizationHeader = authorizationHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("processing authentication for "+request.getRequestURL());

        log.info(authorizationHeader);
        final String requestHeader = request.getHeader(authorizationHeader);
        log.info("request HEADER: "+requestHeader);

        if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
            //todo
            throw new ServletException("Missing or invalid Authorization header");
        }
        String authToken = requestHeader.substring(7);
        try {
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("checking authentication for user '{}'",username);
            if(username !=null) {
                logger.info("security context was null, so authorizating user");

                //todo change this
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                            (userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authorizated user '{}', setting security context", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (IllegalArgumentException e) {
            log.error("an error occured during getting username from token", e);
        } catch (ExpiredJwtException e) {
            log.warn("the token is expired and not valid anymore", e);
        }
        chain.doFilter(request,response);
}
}