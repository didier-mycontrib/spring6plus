package org.mycontrib.hex.bank.persistence.dao;

import java.util.List;

import org.mycontrib.hex.bank.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.NamedNativeQuery;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> getByFirstnameAndLastname(String firstname,String lastname);
    List<CustomerEntity> allCustomers();//via @NamedNativeQuery(name = "CustomerEntity.allCustomers", ...)
}
