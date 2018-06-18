package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.*;
import com.globalcrm.rest.domain.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Mapper
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    Sale dtoToSale(SaleDTO saleDTO);
    SaleDTO saleToDto(Sale sale);

    @Mappings({
            @Mapping(target = "company.contacts", ignore = true),
            @Mapping(target = "sales", ignore = true)
    })
    Contact dtoToContact(ContactDTO contactDTO);

    @Mappings({
            @Mapping(target = "company.contacts", ignore = true),
            @Mapping(target = "sales", ignore = true)
    })
    ContactDTO contactToDto(Contact contact);

    @Mappings({
            @Mapping(target = "accountHolder", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "companies", ignore = true),
    })
    Account dtoToAccount(AccountDTO accountDTO);
    @Mappings({
            @Mapping(target = "accountHolder", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "companies", ignore = true),
    })
    AccountDTO accountToDto(Account account);

    @Mappings({
            @Mapping(target = "sales", ignore = true)
    })
    User dtoToUser(UserDTO userDTO);
    @Mappings({
            @Mapping(target = "sales", ignore = true)
    })
    UserDTO userToDto(User user);

    @Mappings({
            @Mapping(target = "sale", ignore = true)
    })
    Note dtoToNote(NoteDTO noteDTO);
    @Mappings({
            @Mapping(target = "sale", ignore = true)
    })
    NoteDTO noteToDto(Note note);

    @Mappings({
            @Mapping(target = "linkedToSale", ignore = true)
    })
    Task dtoToTask(TaskDTO taskDTO);
    @Mappings({
            @Mapping(target = "linkedToSale", ignore = true)
    })
    TaskDTO taskToDto(Task task);
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
