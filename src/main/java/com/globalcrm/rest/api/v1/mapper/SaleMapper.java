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

    Account dtoToAccount(AccountDTO accountDTO);
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

}
