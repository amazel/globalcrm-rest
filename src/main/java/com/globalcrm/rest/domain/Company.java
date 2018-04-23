package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne()
    private Account account;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private VisibleFor visibleFor;
    @OneToMany
    private Set<Contact> contacts;
    // Not for v1.0
    //private String country;
}
