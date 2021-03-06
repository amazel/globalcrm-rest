package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"account"})
@ToString(exclude = {"account", "contacts"})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "name"})})
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Account account;
    private LocalDateTime creationDateTime;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    @Enumerated(value = EnumType.STRING)
    private VisibleFor visibleFor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Contact> contacts = new HashSet<>();

    public Company addContact(Contact contact) {
        contact.setCompany(this);
        this.contacts.add(contact);
        return this;
    }
}
