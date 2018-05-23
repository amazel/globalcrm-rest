package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface UserAuthMapper {
    UserAuthMapper INSTANCE = Mappers.getMapper(UserAuthMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id")
    })
    User dtoToUser(UserAuthDTO userAuthDTO);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "account.id", target = "accountId"),
    })
    UserAuthDTO userToDto(User user);
}
