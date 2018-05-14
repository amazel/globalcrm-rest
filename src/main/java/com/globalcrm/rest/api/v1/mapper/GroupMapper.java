package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.GroupDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Group;
import com.globalcrm.rest.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group dtoToGroup(GroupDTO groupDTO);

    GroupDTO groupToDto(Group group);

    @Mappings({
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "password", ignore = true)
    })
    User dtoToUser(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "account", ignore = true)
    })
    UserDTO userToDto(User user);

    @Mappings({
            @Mapping(target = "accountHolder.account", ignore = true),
            @Mapping(target = "accountHolder.password", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "companies", ignore = true),
    })
    Account dtoToAccount(AccountDTO accountDTO);

    @Mappings({
            @Mapping(target = "accountHolder.account", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "companies", ignore = true),
    })
    AccountDTO accountToDto(Account account);

}
