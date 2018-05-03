package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.AccountEvent;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Data
public class AccountHistoryDTO {
    private Long id;
    private AccountDTO account;
    private AccountEvent accountEvent;
    private LocalDateTime dateTime;
}
