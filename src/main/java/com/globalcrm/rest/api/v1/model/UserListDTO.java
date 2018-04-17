package com.globalcrm.rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
public class UserListDTO {
    List<UserDTO> users;
}
