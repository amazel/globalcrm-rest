package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserAuthMapper;
import com.globalcrm.rest.api.v1.mapper.UserMapper;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        User newUser = mapper.dtoToUser(userDTO);
        newUser.setId(null);
        newUser.setPassword(null);
        newUser.setEnabled(false);
        return mapper.userToDto(saveUser(accountId, newUser));
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
        return mapper.userToDto(findUserByAccountAndId(accountId, userId));
    }

    public User findUserByAccountAndId(Long accountId, Long userId) {
        Account acct = accountRepository.findById(accountId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(accountId));

        return acct.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> ExceptionFactory.userNotFound(userId));
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() ->  ExceptionFactory.userNotFound(id));
    }
}
