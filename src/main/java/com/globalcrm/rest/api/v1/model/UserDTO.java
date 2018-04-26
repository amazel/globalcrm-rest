package com.globalcrm.rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private AccountDTO account;
    @Size(min = 2, message="Name should have at least 2 characters")
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Email
    private String email;

}
