package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.CompanyMapper;
import com.globalcrm.rest.api.v1.mapper.ContactMapper;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.CompanyRepository;
import com.globalcrm.rest.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    final ContactRepository contactRepository;
    CompanyRepository companyRepository;
    CompanyService companyService;
    CompanyMapper companyMapper = CompanyMapper.INSTANCE;
    ContactMapper contactMapper = ContactMapper.INSTANCE;
    private final UserService userService;

    public ContactServiceImpl(ContactRepository contactRepository, CompanyRepository companyRepository, CompanyService companyService, UserService userService) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ContactDTO createContact(Long acctId, Long companyId, ContactDTO contactDTO) {
        Company company = companyMapper.dtoToCompany(companyService.getCompanyByAccountAndId(acctId, companyId));
        Contact contact = contactMapper.dtoToContact(contactDTO);
        Company companySaved = companyRepository.save(company.addContact(contact));
        return companySaved.getContacts()
                .stream()
                .filter(contact1 -> contact1.getNames().equals(contactDTO.getNames()))
                .findFirst()
                .map(contactMapper::contactToDto)
                .orElseThrow(ExceptionFactory::contactNotCreated);
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> ExceptionFactory.contactNotFound(id));
    }

    @Override
    public List<ContactDTO> getAllContactsByAccount(Long accountId) {
        return companyService.getAllCompaniesByAccount(accountId)
                .stream()
                .flatMap(company -> company.getContacts().stream())
                .map(contactMapper::contactToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findByUserAndId(Long userId, Long contactId) {
        User user = userService.findById(userId);
        Contact contact = findById(contactId);
        if (!user.getAccount().getId().equals(contact.getCompany().getAccount().getId())) {
            ExceptionFactory.contactNotFound(contactId);
        }
        ContactDTO contactDTO = new ContactDTO();
        switch (contact.getVisibleFor()) {
            case ALL:
                contactDTO = contactMapper.contactToDto(contact);
                break;
            case JUST_ME:
            case ME_AND_USERS:
            case ME_AND_GROUPS: {
                if (!contact.getCreatedBy().getId().equals(user.getId())) {
                    ExceptionFactory.contactNotFound(contactId);
                }
                contactDTO = contactMapper.contactToDto(contact);
            }
            break;
            default:
                ExceptionFactory.contactNotFound(contactId);
                break;
        }
        return contactDTO;
    }
}