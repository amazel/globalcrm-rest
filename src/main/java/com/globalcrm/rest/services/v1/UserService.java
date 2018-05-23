package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.domain.User;

public interface UserService {
    UserDTO createAccountUser(Long accountId, UserDTO userDTO);
    UserDTO getUserById(Long userId);
    User findUserByAccountAndId(Long accountId, Long userId);
    User findById(Long id);
}
