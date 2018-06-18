package com.globalcrm.rest.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Hugo Lezama on June - 2018
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"contact"})
@ToString(exclude = {"contact"})
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Contact contact;
    @Enumerated(value = EnumType.STRING)
    private PhoneType phoneType;
    private String phone;

}
