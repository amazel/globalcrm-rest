package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.AccountHistoryDTO;
import com.globalcrm.rest.api.v1.model.SaleHistoryDTO;
import com.globalcrm.rest.domain.AccountHistory;
import com.globalcrm.rest.domain.SaleHistory;

import java.util.List;

/**
 * Created by Hugo Lezama on May - 2018
 */
public interface HistoricalReportService {
    List<AccountHistoryDTO> reportAccountHistory(Long accountId);
    List<SaleHistoryDTO> reportSaletHistory(Long saleId);
}
