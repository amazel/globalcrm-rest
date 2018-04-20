package com.globalcrm.rest.repositories;

import com.globalcrm.rest.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface AccountHistoryRepository extends CrudRepository<AccountHistory,Long> {
}
