package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.SaleStage;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
public class SaleHistoryDTO {
    private Long id;
    private SaleDTO sale;
    private SaleStage currentStage;
    private LocalDateTime dateTime;
}
