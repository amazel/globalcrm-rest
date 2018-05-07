package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.*;
import com.globalcrm.rest.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class SaleMapperTest {
    public static final Long SALE_ID = 123L;
    public static final String DESCRIPTION = "Sale description";
    public static final String TITLE = "SaleTitle";
    public static final Long CONTACT_ID = 432L;
    public static final Long USER_ID = 1L;
    public static final Long NOTE_ID = 56L;
    public static final Long TASK_ID = 798L;

    SaleMapper mapper = SaleMapper.INSTANCE;

    @Test
    public void dtoToSale() {
        SaleDTO saleDTO1 = new SaleDTO();
        saleDTO1.setId(SALE_ID);
        saleDTO1.setCurrentStage(SaleStage.CLOSED_LOST);
        saleDTO1.setDescription(DESCRIPTION);
        saleDTO1.setTitle(TITLE);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(CONTACT_ID);
        contactDTO.getSales().add(saleDTO1);
        saleDTO1.setContact(contactDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.getSales().add(saleDTO1);
        saleDTO1.setResponsible(userDTO);

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(NOTE_ID);
        noteDTO.setSale(saleDTO1);
        saleDTO1.getNotes().add(noteDTO);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(TASK_ID);
        taskDTO.setLinkedToSale(saleDTO1);
        saleDTO1.getTasks().add(taskDTO);

       // log.info(saleDTO1.toString());
        Sale sale = mapper.dtoToSale(saleDTO1);

        assertEquals(TITLE, sale.getTitle());
        assertEquals(DESCRIPTION, sale.getDescription());
        assertEquals(SALE_ID, sale.getId());
        assertEquals(SaleStage.CLOSED_LOST, sale.getCurrentStage());
        assertEquals(USER_ID, sale.getResponsible().getId());
        assertEquals(CONTACT_ID, sale.getContact().getId());
        assertEquals(1, sale.getNotes().size());
        assertEquals(1, sale.getTasks().size());
    }

    @Test
    public void saleToDto() {
        Sale sale = new Sale();
        sale.setId(SALE_ID);
        sale.setCurrentStage(SaleStage.CLOSED_LOST);
        sale.setDescription(DESCRIPTION);
        sale.setTitle(TITLE);

        Contact contact = new Contact();
        contact.setId(CONTACT_ID);
        contact.addSale(sale);
        sale.setContact(contact);

        User user = new User();
        user.setId(USER_ID);
        user.addSale(sale);
        sale.setResponsible(user);

        Note note = new Note();
        note.setId(NOTE_ID);
        sale.addNote(note);

        Task task = new Task();
        task.setId(TASK_ID);
        sale.addTask(task);

        log.info(sale.toString());
        SaleDTO saleDTO = mapper.saleToDto(sale);

        assertEquals(TITLE, saleDTO.getTitle());
        assertEquals(DESCRIPTION, saleDTO.getDescription());
        assertEquals(SALE_ID, saleDTO.getId());
        assertEquals(SaleStage.CLOSED_LOST, saleDTO.getCurrentStage());
        assertEquals(USER_ID, saleDTO.getResponsible().getId());
        assertEquals(CONTACT_ID, saleDTO.getContact().getId());
        assertEquals(1, saleDTO.getNotes().size());
        assertEquals(1, saleDTO.getTasks().size());
    }
}