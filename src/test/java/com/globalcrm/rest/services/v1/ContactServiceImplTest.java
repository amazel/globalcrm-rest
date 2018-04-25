package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.CompanyRepository;
import com.globalcrm.rest.repositories.ContactRepository;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContactServiceImplTest {

    public static final Long CONTACT_ID = 1L;
    public static final String FIRST_NAMES = "FIRST NAMES";
    public static final String LAST_NAMES = "LAST NAMES";


    ContactService contactService;
    CompanyService companyService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    ContactRepository contactRepository;

    @Mock
    CompanyRepository companyRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository, accountRepository);
        contactService = new ContactServiceImpl(companyRepository, companyService);
    }

    /*

    @Test
    public void createContact() {
        //Given
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setNames(FIRST_NAMES);
        contactDTO.setLastNames(LAST_NAMES);
        contactDTO.setCompany(new CompanyDTO());
        contactDTO.setVisibleFor(VisibleFor.ALL);
        contactDTO.getPhones().put(PhoneType.HOME,"535353");
        contactDTO.getPhones().put(PhoneType.SKYPE,"SKYPE 565656");
        contactDTO.getEmails().put(EmailType.PERSONAL,"hleper@gsd.com");
        contactDTO.getEmails().put(EmailType.WORK,"hleper@work.com");

        Contact contact = new Contact();
        contact.setId(CONTACT_ID);
        contact.setNames(FIRST_NAMES);
        contact.setLastNames(LAST_NAMES);
        contact.setCompany(new Company());
        contact.setVisibleFor(VisibleFor.ALL);
        contact.getPhones().put(PhoneType.HOME,"535353");
        contact.getPhones().put(PhoneType.SKYPE,"SKYPE 565656");
        contact.getEmails().put(EmailType.PERSONAL,"hleper@gsd.com");
        contact.getEmails().put(EmailType.WORK,"hleper@work.com");

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        //When
        ContactDTO savedContactDTO = contactService.createContact(contactDTO);

        //Then
        assertEquals(CONTACT_ID, savedContactDTO.getId());
        assertEquals(2, savedContactDTO.getEmails().size());
        assertEquals(2, savedContactDTO.getPhones().size());
        assertEquals(FIRST_NAMES, savedContactDTO.getNames());
        assertEquals(LAST_NAMES, savedContactDTO.getLastNames());
        assertEquals(VisibleFor.ALL, savedContactDTO.getVisibleFor());
    }

    @Test
    public void findById() {
        //Given
        Contact contact = new Contact();
        contact.setNames(FIRST_NAMES);
        contact.setLastNames(LAST_NAMES);
        contact.setCompany(new Company());
        contact.setId(CONTACT_ID);
        contact.setVisibleFor(VisibleFor.ALL);
        contact.getPhones().put(PhoneType.HOME,"535353");
        contact.getPhones().put(PhoneType.SKYPE,"SKYPE 565656");
        contact.getEmails().put(EmailType.PERSONAL,"hleper@gsd.com");
        contact.getEmails().put(EmailType.WORK,"hleper@work.com");
        when(contactRepository.findById(anyLong())).thenReturn(Optional.of(contact));

        //When
        ContactDTO retContact = contactService.findById(CONTACT_ID);

        //Then
        assertEquals(CONTACT_ID, retContact.getId());
        assertEquals(2, retContact.getEmails().size());
        assertEquals(2, retContact.getPhones().size());
        assertEquals(FIRST_NAMES, retContact.getNames());
        assertEquals(LAST_NAMES, retContact.getLastNames());
        assertEquals(VisibleFor.ALL, retContact.getVisibleFor());
    }
    */
}