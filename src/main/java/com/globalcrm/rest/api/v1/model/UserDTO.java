package com.globalcrm.rest.api.v1.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"account"})
@ToString(exclude = {"account"})
public class UserDTO {
    private Long id;
    private AccountDTO account;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Email
    private String email;
    private Byte[] picture;


    private Set<TaskDTO> createdTasks = new HashSet<>();
    private Set<TaskDTO> assignedTasks = new HashSet<>();
    private Set<SaleDTO> sales = new HashSet<>();

}
