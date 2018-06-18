package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.CompanyRepository;
import com.globalcrm.rest.repositories.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ContactServiceImplTest {

    public static final Long CONTACT_ID = 1L;
    public static final Long ACCT_ID = 1L;
    public static final Long COMPANY_ID = 1L;
    public static final String FIRST_NAMES = "FIRST NAMES";
    public static final String LAST_NAMES = "LAST NAMES";
    public static final long USER_ID = 1l;


    ContactService contactService;
    @Mock
    CompanyService companyService;

    @Mock
    ContactRepository contactRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // companyService = new CompanyServiceImpl(companyRepository, accountRepository);
        contactService = new ContactServiceImpl(contactRepository, companyRepository, companyService, userService);
    }

    @Test
    public void createContact() {
        //Given

        Company company = new Company();
        Contact contact = new Contact();
        contact.setNames(FIRST_NAMES);
        contact.setLastNames(LAST_NAMES);
        contact.setCompany(company);
        contact.setVisibleFor(VisibleFor.ALL);
        contact.getPhones().add(
                new Phone(null, null, PhoneType.FAX, "5552342323"));
        contact.getPhones().add(
                new Phone(null, null, PhoneType.WORK, "5552342357"));
        contact.getPhones().add(
                new Phone(null, null, PhoneType.WORK, "5556734677"));

        contact.getEmails().add(
                new Email(null, null, EmailType.PERSONAL, "correo@personal.com"));
        contact.getEmails().add(
                new Email(null, null, EmailType.WORK, "correo@work.com"));
        contact.getEmails().add(
                new Email(null, null, EmailType.WORK, "correo2@work.com"));

        company.getContacts().add(contact);

        when(companyService.getCompanyByAccountAndId(anyLong(), anyLong())).thenReturn(new Company());
        when(contactRepository.saveAndFlush(any(Contact.class))).thenReturn(contact);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setNames(FIRST_NAMES);
        contactDTO.setLastNames(LAST_NAMES);
        contactDTO.setVisibleFor(VisibleFor.ALL);
        contact.getPhones().add(
                new Phone(null, null, PhoneType.FAX, "5552342323"));
        contact.getPhones().add(
                new Phone(null, null, PhoneType.WORK, "5552342357"));
        contact.getPhones().add(
                new Phone(null, null, PhoneType.WORK, "5556734677"));

        contact.getEmails().add(
                new Email(null, null, EmailType.PERSONAL, "correo@personal.com"));
        contact.getEmails().add(
                new Email(null, null, EmailType.WORK, "correo@work.com"));
        contact.getEmails().add(
                new Email(null, null, EmailType.WORK, "correo2@work.com"));

        //When
        ContactDTO savedContactDTO = contactService.createContact(ACCT_ID, COMPANY_ID, USER_ID, contactDTO);

        //Then

        assertEquals(3, savedContactDTO.getEmails().size());
        assertEquals(3, savedContactDTO.getPhones().size());
        assertEquals(FIRST_NAMES, savedContactDTO.getNames());
        assertEquals(LAST_NAMES, savedContactDTO.getLastNames());
        assertEquals(VisibleFor.ALL, savedContactDTO.getVisibleFor());
    }


    @Test
    public void findById() {
    }

    @Test
    public void getAllContactsByAccount() {
    }

    @Test
    public void findByUserAndId() {
    }
}