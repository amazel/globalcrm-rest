package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.ContactMapper;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    ContactRepository contactRepository;
    ContactMapper mapper = ContactMapper.INSTANCE;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO createContact(ContactDTO accountDTO) {
        return mapper.contactToDto(
                contactRepository.save(mapper.dtoToContact(accountDTO)));
    }

    @Override
    public ContactDTO findById(Long id) {
        return contactRepository.findById(id)
                .map(mapper::contactToDto)
                .orElseThrow(() -> ExceptionFactory.contactNotFound(id));
    }
}