package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.domain.VisibleFor;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyMapperTest {

    public static final Long COMPANY_ID = 12323L;
    public static final String COMPANY_NAME = "Company Name";
    public static final String ADDRESS = "123 Evergreen St";
    public static final String CITY = "San Francisco";
    public static final String STATE = "CA";
    public static final String ZIP_CODE = "94087";
    public static final Long ACCOUNT_ID = 234L;
    public static final Long CONTACT_ID = 1232L;
    CompanyMapper mapper = CompanyMapper.INSTANCE;
    @Test
    public void companyToDto() {
        Company company = new Company();

        company.setId(COMPANY_ID);
        company.setName(COMPANY_NAME);
        company.setAddress(ADDRESS);
        company.setCity(CITY);
        company.setState(STATE);
        company.setVisibleFor(VisibleFor.ALL);
        company.setZipCode(ZIP_CODE);

        Account account = new Account();
        account.setId(ACCOUNT_ID);
        account.addCompany(company);
        company.setAccount(account);

        Contact contact = new Contact();
        contact.setId(CONTACT_ID);
        contact.setCompany(company);
        company.addContact(contact);

        CompanyDTO companyDTO = mapper.companyToDto(company);

        assertEquals(COMPANY_ID,companyDTO.getId());
        assertEquals(COMPANY_NAME,companyDTO.getName());
        assertEquals(ADDRESS,companyDTO.getAddress());
        assertEquals(CITY,companyDTO.getCity());
        assertEquals(STATE,companyDTO.getState());
        assertEquals(VisibleFor.ALL,companyDTO.getVisibleFor());
        assertEquals(ZIP_CODE,companyDTO.getZipCode());

        assertEquals(1,companyDTO.getContacts().size());
        assertEquals(ACCOUNT_ID,companyDTO.getAccount().getId());
    }

    @Test
    public void dtoToCompany() {
        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId(COMPANY_ID);
        companyDTO.setName(COMPANY_NAME);
        companyDTO.setAddress(ADDRESS);
        companyDTO.setCity(CITY);
        companyDTO.setState(STATE);
        companyDTO.setVisibleFor(VisibleFor.ALL);
        companyDTO.setZipCode(ZIP_CODE);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ACCOUNT_ID);
        accountDTO.getCompanies().add(companyDTO);
        companyDTO.setAccount(accountDTO);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(CONTACT_ID);
        contactDTO.setCompany(companyDTO);
        companyDTO.getContacts().add(contactDTO);

        Company company = mapper.dtoToCompany(companyDTO);

        assertEquals(COMPANY_ID,company.getId());
        assertEquals(COMPANY_NAME,company.getName());
        assertEquals(ADDRESS,company.getAddress());
        assertEquals(CITY,company.getCity());
        assertEquals(STATE,company.getState());
        assertEquals(VisibleFor.ALL,company.getVisibleFor());
        assertEquals(ZIP_CODE,company.getZipCode());

        assertEquals(1,company.getContacts().size());
        assertEquals(ACCOUNT_ID,company.getAccount().getId());
    }
}