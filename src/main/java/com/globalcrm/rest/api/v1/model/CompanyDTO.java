package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.VisibleFor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"account"})
public class CompanyDTO {

    private Long id;
    private String name;
    private AccountDTO account;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private VisibleFor visibleFor;
    private Set<ContactDTO> contacts = new HashSet<>();
}
