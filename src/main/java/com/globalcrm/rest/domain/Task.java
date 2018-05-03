package com.globalcrm.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private TaskType type;
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    private LocalDateTime dueDate;
    @ManyToOne
    private User assignedTo;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Contact linkedTo;
    private boolean privateTask;
}
