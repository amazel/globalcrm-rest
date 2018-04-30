package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserAuthDTO;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface UserAuthenticationService {
    UserAuthDTO setUserPassword(String email, String password);

    UserAuthDTO login(String email, String password);
}
