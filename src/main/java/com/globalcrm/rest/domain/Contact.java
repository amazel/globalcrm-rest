package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastNames;
    @ElementCollection(targetClass=String.class)
    @MapKeyColumn(name="phone_type")
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyClass(PhoneType.class)
    private Map<PhoneType, String> phones = new HashMap<>();

    @ElementCollection(targetClass=String.class)
    @MapKeyColumn(name="email_type")
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyClass(EmailType.class)
    private Map<EmailType, String> emails = new HashMap<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Company company;
    @Enumerated(value = EnumType.STRING)
    private VisibleFor visibleFor;

    // Not for v1.0
    //private String country;
}
