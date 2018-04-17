package com.globalcrm.rest.repositories;

import com.globalcrm.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
