package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserAuthMapper;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.BadLoginException;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.UserRepository;
import com.globalcrm.rest.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
    final private JwtTokenUtil jwtTokenUtil;
    final private AuthenticationManager authenticationManager;
    private final UserAuthMapper userAuthMapper = UserAuthMapper.INSTANCE;

    public UserAuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserAuthDTO setUserPassword(String email, String password) {
        User user = findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        return userAuthMapper.userToDto(userRepository.save(user));
    }

    @Override
    public UserAuthDTO login(String email, String password) {
        log.info("Login... {},{}", email, password);

        UserAuthDTO reloaded = userAuthMapper.userToDto(userRepository.findByEmail
                (email).orElseThrow(ExceptionFactory::badLoginException));

        log.info(reloaded.toString());
        authenticate(email, password);
        final String token = jwtTokenUtil.generateToken(reloaded);

        reloaded.setJwtToken(token);
        reloaded.setPassword(null);
        return reloaded;
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error(e.getMessage());
            throw new BadLoginException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            ExceptionFactory.badLoginException();
        }
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionFactory.userNotFound(email));
    }
}
