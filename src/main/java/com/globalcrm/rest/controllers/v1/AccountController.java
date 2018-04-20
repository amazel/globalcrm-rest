package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.services.v1.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

/**
 * Created by Hugo Lezama on April - 2018
 */
@RestController
@RequestMapping(AccountController.BASE_URL)
public class AccountController {
    public static final String BASE_URL = "/api/v1/accounts";

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Account createAccount(){
        return accountService.createAccount();
    }
}
