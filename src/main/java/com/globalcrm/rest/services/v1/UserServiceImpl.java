package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserMapper;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private final UserMapper mapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDTO createAccountUser(Long accountId, UserDTO userDTO) {
        User user = mapper.dtoToUser(userDTO);
        Account acct = accountRepository.findById(accountId).orElseThrow(() -> ExceptionFactory
                .accountNotFound(accountId));
        Account savedAcct = accountRepository.save(acct.addUser(user));
        User savedUser = savedAcct.getUsers().stream()
                .filter(user1 -> user1.getEmail().equals(userDTO.getEmail()))
                .findFirst().orElseThrow(ExceptionFactory::userNotCreated);
        return mapper.userToDto(savedUser);
    }
}
