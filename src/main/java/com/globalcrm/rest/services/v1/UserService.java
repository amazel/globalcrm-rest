package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;

public interface UserService {
    UserDTO createAccountUser(Long accountId, UserDTO userDTO);
    UserDTO getUserById(Long accountId, Long userId);
}
