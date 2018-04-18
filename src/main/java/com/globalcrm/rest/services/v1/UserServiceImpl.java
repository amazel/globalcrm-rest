package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserMapper;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.UserRepository;
import com.globalcrm.rest.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(mapper::userToUserDto)
                .orElseThrow(() -> ExceptionFactory.userNotFound(id));
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(mapper.userDtoToUser(userDTO));
        return mapper.userToUserDto(savedUser);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        userDTO.setId(id);
        return saveUser(userDTO);
    }
}
