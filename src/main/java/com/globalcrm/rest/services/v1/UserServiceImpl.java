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
        return mapper.userToDto(saveUser(accountId, mapper.dtoToUser(userDTO)));
    }

    private User saveUser(Long accountId, User user) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(accountId));
        Account savedAcct = accountRepository.save(account.addUser(user));
        return savedAcct.getUsers().stream()
                .filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .findFirst().orElseThrow(ExceptionFactory::userNotCreated);
    }

    public UserDTO getUserById(Long accountId, Long userId) {
        return mapper.userToDto(findUserById(accountId, userId));
    }

    private User findUserById(Long accountId, Long userId) {
        Account acct = accountRepository.findById(accountId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(accountId));

        return acct.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> ExceptionFactory.userNotFound(userId));
    }
}
