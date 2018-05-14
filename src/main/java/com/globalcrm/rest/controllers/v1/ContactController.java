package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.services.v1.CompanyService;
import com.globalcrm.rest.services.v1.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {
    public static final String BASE_URL = "/api/v1/contacts";

    private final CompanyService companyService;
    private final ContactService contactService;

    public ContactController(CompanyService companyService, ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDTO> getContactsByAccountAndCompany(@RequestParam Long accountId, @RequestParam(required = false) Long companyId) {
        log.info("Getting contacts for accountId: " + accountId + " and companyID: " + companyId);
        if (companyId != null) {
            CompanyDTO companyDTO = companyService.getCompanyByAccountAndId(accountId,companyId);
            return new ArrayList<>(companyDTO.getContacts());
        } else {
            return contactService.getAllContactsByAccount(accountId);
        }
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createNewContact(@RequestParam Long accountId, @RequestParam Long companyId, @Valid
    @RequestBody ContactDTO contactDTO) {
        return contactService.createContact(accountId, companyId, contactDTO);
    }
}
