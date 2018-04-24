package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"account"})
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account account;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
