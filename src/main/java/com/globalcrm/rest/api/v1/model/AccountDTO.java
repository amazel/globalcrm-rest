package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private String companyName;
    private String companyWebsite;
    private UserDTO accountHolder;
    private SubscriptionType subscriptionType;
    private LocalDateTime creationDateTime;
    private AccountStatus accountStatus;
    private Set<UserDTO> users;
}