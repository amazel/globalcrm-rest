package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers(Long accountId);
    UserDTO getAccountUserById(Long accountId, Long id);
    UserDTO createAccountUser (Long accountId, UserDTO userDTO);
}
