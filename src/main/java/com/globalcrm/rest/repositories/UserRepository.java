package com.globalcrm.rest.repositories;

import com.globalcrm.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
