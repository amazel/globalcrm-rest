package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToUser(UserDTO userDTO);
    UserDTO userToDto(User user);

    @Mappings({
            @Mapping(target = "accountHolder.account", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    Account dtoToAccount(AccountDTO accountDTO);

    @Mappings({
            @Mapping(target = "accountHolder.account", ignore = true),
            @Mapping(target = "users", ignore = true),
    })
    AccountDTO accountToDto(Account account);



}
