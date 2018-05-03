package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Set<Task> createdTasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedTo")
    private Set<Task> assignedTasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsible")
    private Set<Sale> sales = new HashSet<>();


}
