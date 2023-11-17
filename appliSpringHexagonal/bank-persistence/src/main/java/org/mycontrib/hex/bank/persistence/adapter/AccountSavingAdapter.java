package org.mycontrib.hex.bank.persistence.adapter;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.spi.AccountSaving;
import org.mycontrib.hex.bank.persistence.dao.AccountJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountSavingAdapter implements AccountSaving {
	
	@Autowired
	private AccountJpaRepository accountRepository;

	@Override
	public Account saveOrUpdate(Account account) {
		AccountEntity accountEntity = EntityConverter.INSTANCE.accountToAccountEntity(account);
		accountRepository.save(accountEntity);
		account.setId(String.valueOf(accountEntity.getId()));
		return account;
	}

	@Override
	public void deleteFromId(String id) {
		accountRepository.deleteById(Long.parseLong(id));
	}

}
