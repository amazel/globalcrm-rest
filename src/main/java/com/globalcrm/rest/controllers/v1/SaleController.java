package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.services.v1.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Slf4j
@RestController
@RequestMapping(SaleController.BASE_URL)
public class SaleController {
    public static final String BASE_URL = "/api/v1/sales";

    final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDTO createNewSale(@RequestParam Long accountId, @RequestParam Long userId, @RequestParam Long contactId,
                                 @RequestBody SaleDTO saleDTO) {
        return saleService.createNewSale(accountId, userId, contactId, saleDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SaleDTO> getAllAccountSales(@RequestParam Long accountId) {
        log.info("Getting all sales for account: " + accountId);
        return saleService.getAllSalesByAccount(accountId);
    }
}
