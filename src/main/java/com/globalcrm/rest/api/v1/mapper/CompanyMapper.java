package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO companyToDto(Company company);
    Company dtoToCompany(CompanyDTO companyDTO);

    @Mappings({
            @Mapping(target = "accountHolder", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "companies", ignore = true)

    })
    Account dtoToAccount(AccountDTO accountDTO);
    @Mappings({
            @Mapping(target = "accountHolder", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "companies", ignore = true)

    })
    AccountDTO accountToDto(Account account);

    @Mappings({
            @Mapping(target = "company.account", ignore = true),
            @Mapping(target = "company.contacts", ignore = true),
            @Mapping(target = "sales", ignore = true)
    })
    ContactDTO contactToDto(Contact contact);
    @Mappings({
            @Mapping(target = "company.account", ignore = true),
            @Mapping(target = "company.contacts", ignore = true),
            @Mapping(target = "sales", ignore = true)
    })
    Contact dtoToContact(ContactDTO contactDTO);
}
