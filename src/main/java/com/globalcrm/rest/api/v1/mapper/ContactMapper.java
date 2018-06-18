package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.*;
import com.globalcrm.rest.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToDto(Contact contact);
    Contact dtoToContact(ContactDTO contactDTO);

    @Mappings({
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "sales", ignore = true),
            @Mapping(target = "groups", ignore = true),
            @Mapping(target = "createdTasks", ignore = true),
            @Mapping(target = "assignedTasks", ignore = true)

    })
    User dtoToUser(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "sales", ignore = true),
            @Mapping(target = "createdTasks", ignore = true),
            @Mapping(target = "assignedTasks", ignore = true)

    })
    UserDTO userToDto(User user);

    @Mappings({
            @Mapping(target = "contacts", ignore = true)
    })
    CompanyDTO companyToDto(Company company);

    @Mappings({
            @Mapping(target = "contacts", ignore = true)
    })
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
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "notes", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "responsible", ignore = true)


    })
    Sale dtoToSale(SaleDTO saleDTO);

    @Mappings({
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "notes", ignore = true),
            @Mapping(target = "tasks", ignore = true),
            @Mapping(target = "responsible", ignore = true)

    })
    SaleDTO saleToDto(Sale sale);



    @Mappings({
            @Mapping(target = "contact", ignore = true)

    })
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
