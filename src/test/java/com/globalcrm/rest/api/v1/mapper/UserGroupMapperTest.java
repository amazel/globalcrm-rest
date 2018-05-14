package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.api.v1.model.UserGroupDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.domain.UserGroup;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserGroupMapperTest {

    public static final Long GROUP_ID = 2323L;
    public static final String GROUP_NAME = "Group name";
    public static final Long ACCOUNT_ID = 231L;
    public static final Long USER1_ID = 453L;
    public static final Long USER2_ID = 456L;
    public static final String EMAIL2 = "email2@mail.com";
    public static final String EMAIL1 = "email1@mail.com";


    private UserGroupMapper userGroupMapper = UserGroupMapper.INSTANCE;

    @Test
    public void dtoToGroup() {
        UserGroupDTO userGroupDTO = new UserGroupDTO();
        userGroupDTO.setId(GROUP_ID);
        userGroupDTO.setName(GROUP_NAME);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ACCOUNT_ID);
        userGroupDTO.setAccount(accountDTO);

        UserDTO user1 = new UserDTO();
        user1.setId(USER1_ID);
        user1.setEmail(EMAIL1);

        UserDTO user2 = new UserDTO();
        user2.setId(USER2_ID);
        user2.setEmail(EMAIL2);

        userGroupDTO.getUsers().add(user1);
        userGroupDTO.getUsers().add(user2);

        UserGroup userGroup = userGroupMapper.dtoToUserGroup(userGroupDTO);

        assertEquals(GROUP_ID, userGroup.getId());
        assertEquals(GROUP_NAME, userGroup.getName());
        assertNotNull(userGroup.getAccount());
        assertEquals(ACCOUNT_ID, userGroup.getAccount().getId());
        assertEquals(2, userGroup.getUsers().size());

        for (User u : userGroup.getUsers()) {
            assertNotNull(u.getId());
        }
    }

    @Test
    public void userGroupToDto() {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(GROUP_ID);
        userGroup.setName(GROUP_NAME);

        Account account = new Account();
        account.setId(ACCOUNT_ID);
        userGroup.setAccount(account);

        User user1 = new User();
        user1.setId(USER1_ID);
        user1.setEmail(EMAIL1);

        User user2 = new User();
        user2.setId(USER2_ID);
        user2.setEmail(EMAIL2);

        userGroup.getUsers().add(user1);
        userGroup.getUsers().add(user2);

        UserGroupDTO userGroupDTO = userGroupMapper.userGroupToDto(userGroup);

        assertEquals(GROUP_ID, userGroupDTO.getId());
        assertEquals(GROUP_NAME, userGroupDTO.getName());
        assertNotNull(userGroupDTO.getAccount());
        assertEquals(ACCOUNT_ID, userGroupDTO.getAccount().getId());
        assertEquals(2, userGroupDTO.getUsers().size());

        for (UserDTO u : userGroupDTO.getUsers()) {
            assertNotNull(u.getId());
        }
    }
}