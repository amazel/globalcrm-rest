package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

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
    private LocalDateTime creationDateTime;
    private UserDTO createdBy;
    private ContactType contactType;
    private Set<PhoneDTO> phones = new HashSet<>();
    private Set<EmailDTO> emails = new HashSet<>();
    private CompanyDTO company;
    @NotNull
    private VisibleFor visibleFor;
    private Set<SaleDTO> sales = new HashSet<>();
}
