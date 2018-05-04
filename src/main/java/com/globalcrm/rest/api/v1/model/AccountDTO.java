package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
//@ToString(exclude = {"accountHolder.account","users.account","companies.account"})
public class AccountDTO {
    private Long id;
    private String name;
    private String website;
    @Valid
    @NotNull
    private UserDTO accountHolder;
    private SubscriptionType subscriptionType;
    private AccountStatus accountStatus;
    private Set<UserDTO> users = new HashSet<>();
    private Set<CompanyDTO> companies = new HashSet<>();

}
