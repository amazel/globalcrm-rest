package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserMapper;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
        user.setAccount(account);
       return userRepository.save(user);
    }

    public UserDTO getUserById(Long userId) {
        return mapper.userToDto(findById(userId));
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
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> ExceptionFactory.userNotFound(id));
    }
}
