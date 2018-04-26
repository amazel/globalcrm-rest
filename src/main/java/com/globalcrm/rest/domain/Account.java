package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode
@ToString(exclude = {"accountHolder","users","companies"})
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String website;

    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private User accountHolder;

    @Enumerated(value = EnumType.STRING)
    private SubscriptionType subscriptionType;
    private LocalDateTime creationDateTime;
    private LocalDateTime expirationDateTime;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.NEW;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Company> companies = new HashSet<>();

    public Account addUser(User user) {
        user.setAccount(this);
        this.users.add(user);
        return this;
    }

    public Account addCompany(Company company) {
        company.setAccount(this);
        this.companies.add(company);
        return this;
    }
}
