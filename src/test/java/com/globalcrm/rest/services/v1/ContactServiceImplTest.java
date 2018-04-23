package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ContactServiceImplTest {

    public static final Long CONTACT_ID = 1L;
    public static final String FIRST_NAMES = "FIRST NAMES";
    public static final String LAST_NAMES = "LAST NAMES";

    ContactService contactService;
    @Mock
    ContactRepository contactRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        contactService = new ContactServiceImpl(contactRepository);
    }

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
}