package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.Contact;
import com.globalcrm.rest.domain.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToDto(Contact contact);
    Contact dtoToContact(ContactDTO contactDTO);

    @Mappings({
            @Mapping(target = "contacts", ignore = true),
            @Mapping(target = "account.accountHolder", ignore = true),
            @Mapping(target = "account.users", ignore = true),
            @Mapping(target = "account.companies", ignore = true)

    })
    CompanyDTO companyToDto(Company company);
    @Mappings({
            @Mapping(target = "contacts", ignore = true),
            @Mapping(target = "account.accountHolder", ignore = true),
            @Mapping(target = "account.users", ignore = true),
            @Mapping(target = "account.companies", ignore = true)

    })
    Company dtoToCompany(CompanyDTO companyDTO);


    @Mappings({
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "notes", ignore = true),
            @Mapping(target = "tasks", ignore = true)

    })
    Sale dtoToSale(SaleDTO saleDTO);

    @Mappings({
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "notes", ignore = true),
            @Mapping(target = "tasks", ignore = true)

    })
    SaleDTO saleToDto(Sale sale);


}
