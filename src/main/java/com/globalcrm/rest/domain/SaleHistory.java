package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@Entity
public class SaleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Sale sale;
    @Enumerated(value = EnumType.STRING)
    private SaleStage currentStage;
    private LocalDateTime dateTime;
}
