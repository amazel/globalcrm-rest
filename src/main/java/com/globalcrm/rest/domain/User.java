package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"account"})
@ToString(exclude = {"account"})
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "email"})})
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account account;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;

}
