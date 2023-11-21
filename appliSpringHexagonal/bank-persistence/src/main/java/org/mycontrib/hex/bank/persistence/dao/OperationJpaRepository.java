package org.mycontrib.hex.bank.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.mycontrib.hex.bank.persistence.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationJpaRepository extends JpaRepository<OperationEntity, UUID> {
    public List<OperationEntity> findByTimestampBetween(LocalDateTime firstDate,LocalDateTime lastDate);
    public List<OperationEntity> findByAccountId(Long accountId);
    public List<OperationEntity> findByAccountIdAndTimestampBetween(Long accountId,LocalDateTime firstDate,LocalDateTime lastDate);
}
