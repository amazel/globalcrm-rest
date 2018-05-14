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
    public static final String BASE_URL = "/api/v1/accounts";

    private final CompanyService companyService;
    private final ContactService contactService;

    public ContactController(CompanyService companyService, ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @GetMapping("/{accountId}/companies/{companyId}/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDTO> getCompanyContacts(@PathVariable Long accountId, @PathVariable Long companyId) {
        log.info("Getting contacts for company: " + companyId);

        CompanyDTO companyDTO = companyService.getAccountCompanyById(accountId, companyId);
        return new ArrayList<>(companyDTO.getContacts());
    }

    @PostMapping("/{accountId}/companies/{companyId}/contacts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createNewContact(@PathVariable Long accountId, @PathVariable Long companyId, @Valid
    @RequestBody ContactDTO contactDTO) {
        return contactService.createContact(accountId, companyId, contactDTO);
    }


    @GetMapping("/{accountId}/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDTO> getAllAccountContacts(@PathVariable Long accountId) {
        log.info("Getting contacts for account: " + accountId);
        return contactService.getAllContactsByAccount(accountId);
    }
}
