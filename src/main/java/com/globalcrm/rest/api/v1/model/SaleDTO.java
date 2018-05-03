package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.SaleStage;
import lombok.Data;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
public class SaleDTO {
    private Long id;
    private String title;
    private String description;
    private ContactDTO contact;
    private UserDTO responsible;
    private SaleStage currentStage;
}