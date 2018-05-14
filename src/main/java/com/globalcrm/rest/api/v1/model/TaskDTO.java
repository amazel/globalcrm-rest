package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.TaskStatus;
import com.globalcrm.rest.domain.TaskType;
import com.globalcrm.rest.domain.VisibleFor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on May - 2018
 */
@EqualsAndHashCode(exclude = {"assignedTo", "createdBy", "linkedToContact", "linkedToSale"})
@ToString(exclude = {"assignedTo", "createdBy", "linkedToContact", "linkedToSale"})
@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskType type;
    private TaskStatus status;
    private LocalDateTime dueDate;
    private UserDTO assignedTo;
    private UserDTO createdBy;
    private ContactDTO linkedToContact;
    private SaleDTO linkedToSale;
    private boolean privateTask;
    private VisibleFor visibleFor;
}
