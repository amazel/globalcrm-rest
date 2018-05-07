package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactMapperTest {

    public static final Long CONTACT_ID = 343L;
    public static final String LAST_NAMES = "Lopez Perez";
    public static final String NAMES = "Jose Manuel";
    public static final Long COMPANY_ID = 3434L;
    public static final Long SALE_ID = 4545L;

    ContactMapper mapper = ContactMapper.INSTANCE;

    @Test
    public void contactToDto() {
        Contact contact = new Contact();
        contact.setId(CONTACT_ID);
        contact.setLastNames(LAST_NAMES);
        contact.setNames(NAMES);
        contact.setContactType(ContactType.LEAD);
        contact.setVisibleFor(VisibleFor.ALL);
     //  contact.setPicture("picture".getBytes());

        Company company = new Company();
        company.setId(COMPANY_ID);
        company.addContact(contact);
        contact.setCompany(company);

        //contact.setEmails();
        //contact.setPhones();

        Sale sale = new Sale();
        sale.setId(SALE_ID);
        contact.addSale(sale);

        ContactDTO contactDTO = mapper.contactToDto(contact);

        assertEquals(VisibleFor.ALL ,contactDTO.getVisibleFor());
        assertEquals(LAST_NAMES,contactDTO.getLastNames());
        assertEquals(NAMES,contactDTO.getNames());
        assertEquals(CONTACT_ID,contactDTO.getId());
        assertEquals(1,contactDTO.getSales().size());
        assertEquals(COMPANY_ID,contactDTO.getCompany().getId());
        assertEquals(ContactType.LEAD,contactDTO.getContactType());
    }

    @Test
    public void dtoToContact() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(CONTACT_ID);
        contactDTO.setLastNames(LAST_NAMES);
        contactDTO.setNames(NAMES);
        contactDTO.setContactType(ContactType.LEAD);
        contactDTO.setVisibleFor(VisibleFor.ALL);
        //  contactDTO.setPicture("picture".getBytes());

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(COMPANY_ID);
        companyDTO.getContacts().add(contactDTO);
        contactDTO.setCompany(companyDTO);

        //contactDTO.setEmails();
        //contactDTO.setPhones();

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(SALE_ID);
        contactDTO.getSales().add(saleDTO);

        Contact contact = mapper.dtoToContact(contactDTO);

        assertEquals(VisibleFor.ALL ,contact.getVisibleFor());
        assertEquals(LAST_NAMES,contact.getLastNames());
        assertEquals(NAMES,contact.getNames());
        assertEquals(CONTACT_ID,contact.getId());
        assertEquals(1,contact.getSales().size());
        assertEquals(COMPANY_ID,contact.getCompany().getId());
        assertEquals(ContactType.LEAD,contact.getContactType());
    }
}