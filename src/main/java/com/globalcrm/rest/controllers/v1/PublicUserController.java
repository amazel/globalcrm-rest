package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.mapper.UserAuthMapper;
import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.services.v1.UserAuthenticationService;
import com.globalcrm.rest.services.v1.UserDetailsServiceImpl;
import com.globalcrm.rest.services.v1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping(PublicUserController.BASE_URL)
public class PublicUserController {
    public static final String BASE_URL = "/public";

    final UserAuthenticationService userAuthenticationService;

    public PublicUserController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    UserAuthDTO login(@RequestBody UserAuthDTO userAuthDTO) {
        return userAuthenticationService.login(userAuthDTO.getEmail(), userAuthDTO.getPassword());
    }

    @PostMapping("/set-password")
    UserAuthDTO setUserPassword(@RequestParam String email, @RequestBody String password) {
        return userAuthenticationService.setUserPassword(email, password);
    }

}
