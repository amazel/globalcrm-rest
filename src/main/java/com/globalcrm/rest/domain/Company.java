package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"account"})
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false)
    private Account account;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private VisibleFor visibleFor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Contact> contacts;
    // Not for v1.0
    //private String country;
}
