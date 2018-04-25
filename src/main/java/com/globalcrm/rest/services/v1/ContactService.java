package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.ContactDTO;

/**
 * Created by Hugo Lezama on April - 2018
 */

public interface ContactService {
    ContactDTO createContact(Long acctId, Long companyId, ContactDTO accountDTO);
}
