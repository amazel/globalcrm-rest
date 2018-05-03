package com.globalcrm.rest.repositories;

import com.globalcrm.rest.domain.AccountHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface AccountHistoryRepository extends CrudRepository<AccountHistory, Long> {
    List<AccountHistory> findAllByAccount_Id(Long accountId);
}
