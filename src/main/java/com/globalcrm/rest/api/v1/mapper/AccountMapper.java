package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    Account dtoToAccount(AccountDTO accountDTO);
    AccountDTO accountToDto(Account account);

    @Mappings({
            @Mapping(target = "account.accountHolder", ignore = true),
            @Mapping(target = "account.users", ignore = true),
            @Mapping(target = "account.companies", ignore = true),
            @Mapping(target = "password", ignore = true)

    })
    User userDtoToUser(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "account.accountHolder", ignore = true),
            @Mapping(target = "account.users", ignore = true),
            @Mapping(target = "account.companies", ignore = true),

    })
    UserDTO userToUserDto(User user);

    @Mappings({
            @Mapping(target = "account.accountHolder", ignore = true),
            @Mapping(target = "account.users", ignore = true),
            @Mapping(target = "account.companies", ignore = true)
    })
    CompanyDTO companyToDto(Company company);

    @Mappings({
            @Mapping(target = "account.accountHolder", ignore = true),
            @Mapping(target = "account.users", ignore = true),
            @Mapping(target = "account.companies", ignore = true)
    })
    Company dtoToCompany(CompanyDTO companyDTO);

    @Mappings({
            @Mapping(target = "company.account", ignore = true),
            @Mapping(target = "company.contacts", ignore = true)
    })
    ContactDTO contactToDto(Contact contact);

    @Mappings({
            @Mapping(target = "company.account", ignore = true),
            @Mapping(target = "company.contacts", ignore = true)
    })
    Contact dtoToContact(ContactDTO contactDTO);
}
