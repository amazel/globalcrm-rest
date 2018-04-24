package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@Entity
public class AccountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Account account;
    @Enumerated(value = EnumType.STRING)
    private AccountEvent accountEvent;
    private LocalDateTime dateTime;

}
