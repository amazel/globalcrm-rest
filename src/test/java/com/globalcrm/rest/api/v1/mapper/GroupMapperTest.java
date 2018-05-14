package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.GroupDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Group;
import com.globalcrm.rest.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupMapperTest {

    public static final Long GROUP_ID = 2323L;
    public static final String GROUP_NAME = "Group name";
    public static final Long ACCOUNT_ID = 231L;
    public static final Long USER1_ID = 453L;
    public static final Long USER2_ID = 456L;
    public static final String EMAIL2 = "email2@mail.com";
    public static final String EMAIL1 = "email1@mail.com";


    private GroupMapper groupMapper = GroupMapper.INSTANCE;

    @Test
    public void dtoToGroup() {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(GROUP_ID);
        groupDTO.setName(GROUP_NAME);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ACCOUNT_ID);
        groupDTO.setAccount(accountDTO);

        UserDTO user1 = new UserDTO();
        user1.setId(USER1_ID);
        user1.setEmail(EMAIL1);

        UserDTO user2 = new UserDTO();
        user2.setId(USER2_ID);
        user2.setEmail(EMAIL2);

        groupDTO.getUsers().add(user1);
        groupDTO.getUsers().add(user2);

        Group group = groupMapper.dtoToGroup(groupDTO);

        assertEquals(GROUP_ID,group.getId());
        assertEquals(GROUP_NAME,group.getName());
        assertNotNull(group.getAccount());
        assertEquals(ACCOUNT_ID,group.getAccount().getId());
        assertEquals(2,group.getUsers().size());

        for(User u:group.getUsers()){
            assertNotNull(u.getId());
        }
    }

    @Test
    public void groupToDto() {
        Group group = new Group();
        group.setId(GROUP_ID);
        group.setName(GROUP_NAME);

        Account account = new Account();
        account.setId(ACCOUNT_ID);
        group.setAccount(account);

        User user1 = new User();
        user1.setId(USER1_ID);
        user1.setEmail(EMAIL1);

        User user2 = new User();
        user2.setId(USER2_ID);
        user2.setEmail(EMAIL2);

        group.getUsers().add(user1);
        group.getUsers().add(user2);

        GroupDTO groupDTO = groupMapper.groupToDto(group);

        assertEquals(GROUP_ID, groupDTO.getId());
        assertEquals(GROUP_NAME, groupDTO.getName());
        assertNotNull(groupDTO.getAccount());
        assertEquals(ACCOUNT_ID, groupDTO.getAccount().getId());
        assertEquals(2, groupDTO.getUsers().size());

        for (UserDTO u : groupDTO.getUsers()) {
            assertNotNull(u.getId());
        }
    }
}