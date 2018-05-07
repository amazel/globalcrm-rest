package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.SaleStage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on May - 2018
 */
@EqualsAndHashCode(exclude = {"contact", "responsible"})
@ToString(exclude = {"contact", "responsible"})
@Data
public class SaleDTO {
    private Long id;
    private String title;
    private String description;
    private ContactDTO contact;
    private UserDTO responsible;
    private SaleStage currentStage;
    private Set<NoteDTO> notes = new HashSet<>();
    private Set<TaskDTO> tasks = new HashSet<>();
}