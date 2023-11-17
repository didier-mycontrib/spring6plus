package org.mycontrib.hex.bank.persistence.dao;

import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {

}
