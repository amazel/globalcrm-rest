package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"account","users"})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "name"})})
@Entity
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Account account;
    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();
}