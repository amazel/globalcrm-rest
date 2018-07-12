package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on Jun - 2018
 */
@Data
@Entity
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Task task;
    private LocalDateTime creationDateTime;

}
