package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Contact;

import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */

public interface ContactService {
    ContactDTO createContact(Long acctId, Long companyId, Long userId, ContactDTO accountDTO);
    Contact findById(Long id);

    List<ContactDTO> getAllContactsByAccount(Long accountId);

    ContactDTO findByUserAndId(Long userId, Long contactId);

    void deleteContact(Long contactId);
}
