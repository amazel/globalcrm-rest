package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.CompanyRepository;
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


    ContactService contactService;
    @Mock
    CompanyService companyService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    CompanyRepository companyRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
       // companyService = new CompanyServiceImpl(companyRepository, accountRepository);
        contactService = new ContactServiceImpl(companyRepository, companyService);
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
        contact.getPhones().put(PhoneType.HOME, "535353");
        contact.getPhones().put(PhoneType.SKYPE, "SKYPE 565656");
        contact.getEmails().put(EmailType.PERSONAL, "hleper@gsd.com");
        contact.getEmails().put(EmailType.WORK, "hleper@work.com");

        company.getContacts().add(contact);

        when(companyService.getAccountCompanyById(anyLong(), anyLong())).thenReturn(new CompanyDTO());
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setNames(FIRST_NAMES);
        contactDTO.setLastNames(LAST_NAMES);
        contactDTO.setVisibleFor(VisibleFor.ALL);
        contactDTO.getEmails().put(EmailType.PERSONAL, "hleper@gsd.com");
        contactDTO.getEmails().put(EmailType.WORK, "hleper@work.com");
        contactDTO.getPhones().put(PhoneType.SKYPE, "SKYPE 565656");
        contactDTO.getPhones().put(PhoneType.HOME, "535353");

        //When
        ContactDTO savedContactDTO = contactService.createContact(ACCT_ID, COMPANY_ID, contactDTO);

        //Then

        assertEquals(2, savedContactDTO.getEmails().size());
        assertEquals(2, savedContactDTO.getPhones().size());
        assertEquals(FIRST_NAMES, savedContactDTO.getNames());
        assertEquals(LAST_NAMES, savedContactDTO.getLastNames());
        assertEquals(VisibleFor.ALL, savedContactDTO.getVisibleFor());
    }
}