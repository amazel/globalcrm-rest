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
                .map(user -> mapper.userToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> mapper.userToUserDto(user))
                .orElseThrow(() -> ExceptionFactory.userNotFound(id));
    }

    @Override
    public UserDTO saveUser(Long accountId, UserDTO userDTO) {
        User user = mapper.userDtoToUser(userDTO);
        Account acct = accountRepository.findById(accountId).orElse(null);
        user.setAccount(acct);
        User savedUser = userRepository.save(user);
        return mapper.userToUserDto(savedUser);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long accountId, Long id, UserDTO userDTO) {
        userDTO.setId(id);
        return saveUser(accountId, userDTO);
    }
}
