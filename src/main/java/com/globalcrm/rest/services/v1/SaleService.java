package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.domain.Sale;

/**
 * Created by Hugo Lezama on May - 2018
 */
public interface SaleService {
    SaleDTO createNewSale(Long userId, Long contactId, SaleDTO saleDTO);
}
