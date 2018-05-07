package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Contact;

/**
 * Created by Hugo Lezama on April - 2018
 */

public interface ContactService {
    ContactDTO createContact(Long acctId, Long companyId, ContactDTO accountDTO);
    Contact findById(Long id);
}
