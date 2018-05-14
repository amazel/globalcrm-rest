package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
public class UserGroupDTO {

    private Long id;
    private String name;
    private AccountDTO account;
    private Set<UserDTO> users = new HashSet<>();
}
