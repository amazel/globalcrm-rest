package com.globalcrm.rest.controllers.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RestController
@RequestMapping(UserGroupController.BASE_URL)
public class UserGroupController {
    public static final String BASE_URL = "/api/v1/userGroups";
}
