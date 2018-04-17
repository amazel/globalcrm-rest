package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.repositories.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Hugo Lezama on April - 2018
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceImplIT {
    @Autowired
    UserRepository userRepository;

    UserService userService;
}
