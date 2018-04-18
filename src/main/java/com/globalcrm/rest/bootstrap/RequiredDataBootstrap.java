package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Component
public class RequiredDataBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;

    public RequiredDataBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        loadAdminUser();
    }

    private void loadAdminUser() {
        log.info("Loading admin user");
        User adminUser = new User();
        adminUser.setFirstName("Hugo");
        adminUser.setLastName("Lezama");
        adminUser.setEmail("hugo.lezama@globalcrm.com");
        userRepository.save(adminUser);
    }
}
