package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"company"})
@ToString(exclude = {"company"})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "names"})})
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastNames;
    private LocalDateTime creationDateTime;
    @ManyToOne
    private User createdBy;
    @ManyToOne(optional = false)
    private Company company;

    @Enumerated(value = EnumType.STRING)
    private ContactType contactType;

    @Enumerated(value = EnumType.STRING)
    private VisibleFor visibleFor;
//    private Set<User> visibleForUsers = new HashSet<>();
//    private Set<Group> visibleForGroups = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private Set<Phone> phones = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
    private Set<Email> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contact")
    private Set<Sale> sales = new HashSet<>();

    public void addSale(Sale sale) {
        sale.setContact(this);
        this.sales.add(sale);
    }

    public void addPhone(Phone phone) {
        phone.setContact(this);
        this.phones.add(phone);
    }
    
    public void addEmail(Email email) {
        email.setContact(this);
        this.emails.add(email);
    }
}
