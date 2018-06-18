package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.CompanyMapper;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;
    AccountRepository accountRepository;
    CompanyMapper mapper = CompanyMapper.INSTANCE;

    public CompanyServiceImpl(CompanyRepository companyRepository, AccountRepository accountRepository) {
        this.companyRepository = companyRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CompanyDTO createCompany(Long accountId, CompanyDTO companyDTO) {
        Account acct = accountRepository.findById(accountId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(accountId));
        Company company = mapper.dtoToCompany(companyDTO);
        company.setAccount(acct);
        for(Contact c: company.getContacts()) {
            c.setCompany(company);
        }
        company.setCreationDateTime(LocalDateTime.now());

        return mapper.companyToDto(companyRepository.saveAndFlush(company));
    }

    @Override
    public CompanyDTO getCompanyDTO(Long accountId, Long companyId) {
        return mapper.companyToDto(getCompanyByAccountAndId(accountId, companyId));
    }

    @Override
    public Company getCompanyByAccountAndId(Long accountId, Long companyId){
        return getAllCompaniesByAccount(accountId)
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElseThrow(() -> ExceptionFactory.companyNotFound(companyId));
    }

    @Override
    public List<Company> getAllCompaniesByAccount(Long accountId) {
        Account acct = accountRepository.findById(accountId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(accountId));
        return new ArrayList<>(acct.getCompanies());
    }
}
