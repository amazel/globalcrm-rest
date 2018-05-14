package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.domain.Company;

import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface CompanyService {
    CompanyDTO createCompany(Long accountId, CompanyDTO companyDTO);
    List<Company> getAllCompaniesByAccount(Long accountId);
    CompanyDTO getAccountCompanyById(Long accountId, Long companyId);
}
