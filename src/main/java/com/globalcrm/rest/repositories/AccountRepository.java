package com.globalcrm.rest.repositories;

import com.globalcrm.rest.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface AccountRepository extends JpaRepository<Account,Long> {
}
