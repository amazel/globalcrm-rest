package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.services.v1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/accounts";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{accountId}/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllAccountUsers(@PathVariable Long accountId) {
        return userService.getAllUsers(accountId);
    }

    @GetMapping("/{accountId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long accountId, @PathVariable Long userId) {
        log.info("Getting User: " + userId);
        return userService.getAccountUserById(accountId, userId);
    }

    @PostMapping("/{accountId}/users/new")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@PathVariable Long accountId, @Valid @RequestBody UserDTO userDTO) {
        return userService.createAccountUser(accountId, userDTO);
    }
/*
    @DeleteMapping({"/{accountId}/users/{userId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long accountId, @PathVariable Long userId) {
        userService.deleteAccountUserById(accountId, userId);
    }
*/
}
