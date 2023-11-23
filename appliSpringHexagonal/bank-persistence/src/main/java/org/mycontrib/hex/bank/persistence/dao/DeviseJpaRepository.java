package org.mycontrib.hex.bank.persistence.dao;

import org.mycontrib.hex.bank.persistence.entity.DeviseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseJpaRepository extends JpaRepository<DeviseEntity, String> {
    
}
