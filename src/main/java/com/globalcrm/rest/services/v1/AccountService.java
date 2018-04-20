package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.AccountEvent;
import com.globalcrm.rest.domain.AccountHistory;
import com.globalcrm.rest.domain.AccountStatus;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO findById(Long id);
    AccountDTO manageAccountStatus(Long acctId, AccountStatus acctStatus);
    AccountHistory generateAccountHistoryRecord(Account account, AccountEvent event);
}
