package com.globalcrm.rest.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Hugo Lezama on June - 2018
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"contact"})
@ToString(exclude = {"contact"})
@NoArgsConstructor
@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Contact contact;
    @Enumerated(value = EnumType.STRING)
    private EmailType emailType;
    private String email;
}
