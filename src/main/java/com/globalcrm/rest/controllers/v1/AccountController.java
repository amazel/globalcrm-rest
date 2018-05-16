package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.services.v1.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RestController
@RequestMapping(AccountController.BASE_URL)
public class AccountController {
    public static final String BASE_URL = "/api/v1/accounts";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{acctId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccountById(@PathVariable Long acctId) {
        log.info("Getting Account: " + acctId);
        return accountService.findDTOById(acctId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        log.info("Creating new account");
        return accountService.createAccount(accountDTO);
    }
}
