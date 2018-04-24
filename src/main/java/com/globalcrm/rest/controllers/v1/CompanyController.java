package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.services.v1.AccountService;
import com.globalcrm.rest.services.v1.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController {
    public static final String BASE_URL = "/api/v1/accounts";

    CompanyService companyService;
    AccountService accountService;

    public CompanyController(CompanyService companyService, AccountService accountService) {
        this.companyService = companyService;
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDTO> getCompanies(@PathVariable Long accountId) {
        log.info("Getting companies for account: " + accountId);
        return new ArrayList<>(accountService.findById(accountId).getCompanies());
    }

}
