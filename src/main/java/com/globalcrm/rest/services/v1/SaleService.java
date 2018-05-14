package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.SaleDTO;

import java.util.List;

/**
 * Created by Hugo Lezama on May - 2018
 */
public interface SaleService {
    SaleDTO createNewSale(Long accountId, Long userId, Long contactId, SaleDTO saleDTO);

    List<SaleDTO> getAllSalesByAccount(Long accountId);
}
