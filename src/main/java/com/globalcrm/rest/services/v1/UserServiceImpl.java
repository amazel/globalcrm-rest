package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserMapper;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.exceptions.ResourceNotFoundException;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserDTO> getAllUsers(Long accountId) {

        Account acct = accountRepository.findById(accountId).orElseThrow(ResourceNotFoundException::new);
        return acct.getUsers()
                .stream()
                .map(mapper::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getAccountUserById(Long accountId, Long userId) {
        Account acct = accountRepository.findById(accountId).orElseThrow(() -> ExceptionFactory
                .accountNotFound(accountId));
        return mapper.userToDto(acct.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> ExceptionFactory.userNotFound(userId)));
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

/*
    public void deleteAccountUserById(Long accountId, Long id) {
        Account acct = accountRepository.findById(accountId).orElseThrow(() -> ExceptionFactory
                .accountNotFound(accountId));
        acct.getUsers().stream()
                .filter(user -> user.getId().equals(id))
                .map(user -> acct.getUsers().remove(user))
                .collect(Collectors.toList());
        accountRepository.save(acct);
    }
    */
}
