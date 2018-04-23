package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.EmailType;
import com.globalcrm.rest.domain.PhoneType;
import com.globalcrm.rest.domain.VisibleFor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long id;
    private String names;
    private String lastNames;
    private Map<PhoneType, String> phones = new HashMap<>();
    private Map<EmailType, String> emails = new HashMap<>();
    private CompanyDTO company;
    private VisibleFor visibleFor;
}
