package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.services.v1.AccountService;
import com.globalcrm.rest.services.v1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/accounts";

    private final AccountService accountService;
    private final UserService userService;

    public UserController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/{accountId}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllAccountUsers(@PathVariable Long accountId) {
        AccountDTO accountDTO = accountService.findById(accountId);
        return new ArrayList<>(accountDTO.getUsers());
    }

    @GetMapping("/{accountId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long accountId, @PathVariable Long userId) {
        log.info("Getting User: " + userId);
        AccountDTO accountDTO = accountService.findById(accountId);
        return accountDTO.getUsers()
                .stream()
                .filter(userDTO -> userDTO.getId().equals(userId))
                .findFirst().orElseThrow(() ->  ExceptionFactory.userNotFound(userId));
    }

    @PostMapping("/{accountId}/users/new")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@PathVariable Long accountId, @Valid @RequestBody UserDTO userDTO) {
        return userService.createAccountUser(accountId, userDTO);
    }
}
