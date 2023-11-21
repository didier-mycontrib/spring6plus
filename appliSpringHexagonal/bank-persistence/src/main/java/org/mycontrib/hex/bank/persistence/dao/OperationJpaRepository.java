package org.mycontrib.hex.bank.persistence.dao;

import java.util.UUID;

import org.mycontrib.hex.bank.persistence.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationJpaRepository extends JpaRepository<OperationEntity, UUID> {
    
}
