package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.CompanyMapper;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;
    CompanyMapper mapper = CompanyMapper.INSTANCE;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        return mapper.companyToDto(companyRepository.save(mapper.dtoToCompany(companyDTO)));
    }
}
