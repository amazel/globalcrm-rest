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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    ContactRepository contactRepository;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ContactDTO createContact(Long acctId, Long companyId, Long userId, ContactDTO contactDTO) {
        Company company = companyService.getCompanyByAccountAndId(acctId, companyId);
        Contact contact = contactMapper.dtoToContact(contactDTO);
        User createdBy = userService.findById(userId);
        contact.getPhones().clear();
        contact.getEmails().clear();
        contact.getSales().clear();

        contactDTO.getPhones().iterator().forEachRemaining(
                phone -> contact.addPhone(contactMapper.dtoToPhone(phone)));
        contactDTO.getEmails().iterator().forEachRemaining(
                email -> contact.addEmail(contactMapper.dtoToEmail(email)));
        contactDTO.getSales().iterator().forEachRemaining(
                sale -> contact.addSale(contactMapper.dtoToSale(sale)));

        contact.setCreationDateTime(LocalDateTime.now());
        contact.setCompany(company);
        contact.setCreatedBy(createdBy);
        return contactMapper.contactToDto(contactRepository.saveAndFlush(contact));
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