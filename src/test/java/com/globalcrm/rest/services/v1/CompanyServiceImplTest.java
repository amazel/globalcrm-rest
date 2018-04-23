package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.domain.VisibleFor;
import com.globalcrm.rest.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CompanyServiceImplTest {

    public static final String NAME = "Company Name";
    public static final String CITY = "CITY";
    public static final String ADDRESS = "ADDRESS";
    public static final String ZIP_CODE = "07890";
    public static final String STATE = "STATE";
    public static final Long ACCT_ID = 1L;

    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository);
    }

    @Test
    public void createCompany() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setAddress(ADDRESS);
        companyDTO.setCity(CITY);
        companyDTO.setName(NAME);
        companyDTO.setState(STATE);
        companyDTO.setVisibleFor(VisibleFor.ALL);
        companyDTO.setZipCode(ZIP_CODE);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ACCT_ID);
        companyDTO.setAccount(accountDTO);


    }
}