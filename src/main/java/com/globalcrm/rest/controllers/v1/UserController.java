package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;
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

    public static final String BASE_URL = "/api/v1/users";

    private final AccountService accountService;
    private final UserService userService;

    public UserController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllAccountUsers(@RequestParam Long accountId) {
        AccountDTO accountDTO = accountService.findDTOById(accountId);
        return new ArrayList<>(accountDTO.getUsers());
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long userId) {
        log.info("Getting User: " + userId);
      return userService.getUserById(userId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@RequestParam Long accountId, @Valid @RequestBody UserDTO userDTO) {
        return userService.createAccountUser(accountId, userDTO);
    }
}