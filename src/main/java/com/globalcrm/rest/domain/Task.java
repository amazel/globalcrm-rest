package com.globalcrm.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"assignedTo","createdBy","linkedToContact","linkedToSale"})
@ToString(exclude = {"assignedTo","createdBy","linkedToContact","linkedToSale"})
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
    private Contact linkedToContact;
    @ManyToOne
    private Sale linkedToSale;

    private boolean privateTask;
}
