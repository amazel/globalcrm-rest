package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.domain.Sale;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Hugo Lezama on May - 2018
 */
@EqualsAndHashCode(exclude = {"contact","sale"})
@ToString(exclude = {"contact","sale"})
@Data
public class NoteDTO {
    private Long id;
    private String note;
    private ContactDTO contact;
    private SaleDTO sale;
}
