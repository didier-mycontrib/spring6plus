package org.mycontrib.hex.bank.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.spi.AccountLoading;
import org.mycontrib.hex.bank.persistence.dao.AccountJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountLoadingAdapter implements AccountLoading {
	
	@Autowired
	private AccountJpaRepository accountRepository;


	@Override
	public Optional<Account> loadById(String id) {
		return Optional.of(
				  EntityConverter.INSTANCE.map(
				      accountRepository.findById(Long.parseLong(id)).orElse(null),
                      Account.class)
				  );
	}

	@Override
	public boolean existsWithThisId(String id) {
		return accountRepository.existsById(Long.parseLong(id));
	}

	@Override
	public List<Account> loadAll() {
		return EntityConverter.INSTANCE.map(accountRepository.findAll(),
				                                     Account.class);
	}

	@Override
	public List<Account> loadWithMinimumBalance(Double minBalance) {
		List<AccountEntity> listAccountEntities = accountRepository.getByBalanceGreaterThanEqual(minBalance);
		return EntityConverter.INSTANCE.map(listAccountEntities,Account.class);
	}

}
