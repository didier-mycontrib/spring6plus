package org.mycontrib.hex.bank.persistence.dao;

import java.util.List;

import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.mycontrib.hex.bank.persistence.entity.AccountOwnershipCompositePk;
import org.mycontrib.hex.bank.persistence.entity.AccountOwnershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOwnershipJpaRepository extends JpaRepository<AccountOwnershipEntity, AccountOwnershipCompositePk> {
	public List<AccountEntity> accountListFromCustomerId(Long customerId);//via @NamedQuery
}
