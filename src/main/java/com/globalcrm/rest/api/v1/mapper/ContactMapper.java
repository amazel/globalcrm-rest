package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.domain.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToDto(Contact contact);
    Contact dtoToContact(ContactDTO contactDTO);
}
