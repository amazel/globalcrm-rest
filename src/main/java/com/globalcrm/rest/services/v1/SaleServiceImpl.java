package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.SaleMapper;
import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.SaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hugo Lezama on May - 2018
 */
@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserService userService;
    private final ContactService contactService;
    private final SaleMapper saleMapper = SaleMapper.INSTANCE;

    public SaleServiceImpl(UserService userService, ContactService contactService, SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.userService = userService;
        this.contactService = contactService;
    }

    @Transactional
    @Override
    public SaleDTO createNewSale(Long accountId, Long userId, Long contactId, SaleDTO saleDTO) {
        log.info("Saving sale, AcctId: {}, userID: {}, contactId: {}",accountId,userId,contactId);
        User user = userService.findUserByAccountAndId(accountId,userId);
        Contact contact = contactService.findById(contactId);
        Sale sale = saleMapper.dtoToSale(saleDTO);

        sale.setContact(contact);
        sale.setResponsible(user);
        sale.setCurrentStage(SaleStage.INITIAL_CONTACT);
        sale.setId(null);

        user.addSale(sale);
        contact.addSale(sale);

        Sale savedSale = saleRepository.save(sale);

        return saleMapper.saleToDto(savedSale);
    }

    public List<SaleDTO> getAllSalesByAccount(Long accountId){
        Account account = new Account();
        return account.getUsers()
                .stream()
                .flatMap(user -> user.getSales().stream())
                .map(saleMapper::saleToDto)
                .collect(Collectors.toList());
    }
}
