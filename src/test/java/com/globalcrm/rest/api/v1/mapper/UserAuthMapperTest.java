package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserAuthMapperTest {
    UserAuthMapper userAuthMapper = UserAuthMapper.INSTANCE;

    @Test
    public void dtoToUser() {
        User user = new User();
        user.setEmail("email");
        user.setEnabled(true);
        UserAuthDTO userAuthDTO = userAuthMapper.userToDto(user);

        assertTrue(userAuthDTO.isEnabled());
    }

    @Test
    public void userToDto() {
    }
}