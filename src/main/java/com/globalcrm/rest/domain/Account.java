package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyWebsite;

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private User accountHolder;

    @Enumerated(value = EnumType.STRING)
    private SubscriptionType subscriptionType;
    private LocalDateTime creationDateTime;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.NEW;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<User> users = new HashSet<>();

    public Account addUser(User user){
        user.setAccount(this);
        this.users.add(user);
        return this;
    }
}
