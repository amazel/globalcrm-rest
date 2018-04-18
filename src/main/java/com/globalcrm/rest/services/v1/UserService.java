package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);

    UserDTO saveUser (UserDTO userDTO);
    UserDTO updateUser (Long id, UserDTO userDTO);
    void deleteUser (Long id);
}
