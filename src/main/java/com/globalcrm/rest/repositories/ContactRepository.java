package com.globalcrm.rest.repositories;

import com.globalcrm.rest.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hugo Lezama on April - 2018
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
