package com.globalcrm.rest.config;

import com.globalcrm.rest.security.JwtAccessDeniedHandler;
import com.globalcrm.rest.security.JwtAuthorizationTokenFilter;
import com.globalcrm.rest.security.JwtTokenUtil;
import com.globalcrm.rest.security.JwtUnauthorizedHandler;
import com.globalcrm.rest.services.v1.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final private JwtUnauthorizedHandler unauthorizedHandler;
    final private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    final private UserDetailsService userDetailsService;
    final private JwtTokenUtil jwtTokenUtil;

    public SecurityConfig(JwtUnauthorizedHandler unauthorizedHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler, UserDetailsServiceImpl userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("Accessing Security configure()");
        httpSecurity
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers("/auth/**/**").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService, jwtTokenUtil, tokenHeader);
        httpSecurity
                .addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/h2-console/**/**")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/webjars/**")
                .antMatchers(
                        HttpMethod.POST,
                        "/auth/**/**"
                )
//
//                // allow anonymous resource requests
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                );
    }
}
