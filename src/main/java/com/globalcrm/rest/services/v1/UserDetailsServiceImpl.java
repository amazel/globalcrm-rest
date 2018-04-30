package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserAuthMapper;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.UserRepository;
import com.globalcrm.rest.security.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    UserAuthMapper userAuthMapper = UserAuthMapper.INSTANCE;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAuthDTO loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAuthMapper.userToDto(findByEmail(email));
    }

    private User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionFactory.userNotFound(email));
    }
}
