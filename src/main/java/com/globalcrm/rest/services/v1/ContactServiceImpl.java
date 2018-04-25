package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.CompanyMapper;
import com.globalcrm.rest.api.v1.mapper.ContactMapper;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    CompanyRepository companyRepository;
    CompanyService companyService;
    CompanyMapper companyMapper = CompanyMapper.INSTANCE;
    ContactMapper contactMapper = ContactMapper.INSTANCE;

    public ContactServiceImpl(CompanyRepository companyRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @Override
    @Transactional
    public ContactDTO createContact(Long acctId, Long companyId, ContactDTO contactDTO) {
        Company company = companyMapper.dtoToCompany(companyService.getAccountCompanyById(acctId, companyId));
        Contact contact = contactMapper.dtoToContact(contactDTO);
        Company companySaved = companyRepository.save(company.addContact(contact));
        return companySaved.getContacts()
                .stream()
                .filter(contact1 -> contact1.getNames().equals(contactDTO.getNames()))
                .findFirst()
                .map(contactMapper::contactToDto)
                .orElseThrow(ExceptionFactory::contactNotCreated);
    }
}