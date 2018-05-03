package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;

import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface CompanyService {
    CompanyDTO createCompany(Long accountId, CompanyDTO companyDTO);
    //List<CompanyDTO> getAccountCompanies(Long accountId);
    CompanyDTO getAccountCompanyById(Long accountId, Long companyId);
}