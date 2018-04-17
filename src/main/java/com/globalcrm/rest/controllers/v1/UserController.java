package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.api.v1.model.UserListDTO;
import com.globalcrm.rest.services.v1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getAllUsers(){
        return new UserListDTO(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long userId){
        log.info("Getting User: "+userId);
        return userService.getUserById(userId);
    }
}
