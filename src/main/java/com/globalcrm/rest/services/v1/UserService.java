package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
}
