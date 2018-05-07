package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.*;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@EqualsAndHashCode(exclude = {"company"})
@ToString(exclude = {"company"})
public class ContactDTO {
    private Long id;
    private String names;
    private String lastNames;
    private Byte[] picture;
    private ContactType contactType;
    private Map<PhoneType, String> phones = new HashMap<>();
    private Map<EmailType, String> emails = new HashMap<>();
    private CompanyDTO company;
    private VisibleFor visibleFor;
    private Set<SaleDTO> sales = new HashSet<>();
}
