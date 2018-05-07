package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"contact","sale"})
@ToString(exclude = {"contact","sale"})
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String note;
    @ManyToOne
    private Contact contact;
    @ManyToOne
    private Sale sale;

}
