package com.globalcrm.rest.domain;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(optional = false)
    private Contact contact;
    @ManyToOne(optional = false)
    private User responsible;
    @Enumerated(value = EnumType.STRING)
    private SaleStage currentStage;
}