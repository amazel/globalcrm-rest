package com.globalcrm.rest.api.v1.model;

import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.domain.EmailType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Hugo Lezama on June - 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private Long id;
    private ContactDTO contact;
    private EmailType emailType;
    private String email;
}
