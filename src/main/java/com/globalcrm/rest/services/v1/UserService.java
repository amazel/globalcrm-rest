package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;

public interface UserService {
    UserDTO createAccountUser(Long accountId, UserDTO userDTO);
}
