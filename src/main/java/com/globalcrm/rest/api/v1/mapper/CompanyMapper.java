package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.*;
import com.globalcrm.rest.domain.*;
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

    Phone dtoToPhone(PhoneDTO phoneDTO);
    @Mappings({
            @Mapping(target = "contact", ignore = true)

    })
    PhoneDTO phoneToDto(Phone phone);


    @Mappings({
            @Mapping(target = "contact", ignore = true)

    })
    Email dtoToEmail(EmailDTO emailDTO);

    @Mappings({
            @Mapping(target = "contact", ignore = true)

    })
    EmailDTO emailToDto(Email email);
}
