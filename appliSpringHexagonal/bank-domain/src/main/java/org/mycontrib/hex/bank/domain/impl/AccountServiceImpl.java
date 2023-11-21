package org.mycontrib.hex.bank.domain.impl;

import java.util.List;

import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.entity.Operation;
import org.mycontrib.hex.bank.domain.spi.AccountLoader;
import org.mycontrib.hex.bank.domain.spi.AccountSaver;
import org.mycontrib.hex.generic.domain.impl.GenericQueryingAndLifeCycleAbstract;

//@Service and @Transactional will be done outside this agnostic code
public class AccountServiceImpl extends GenericQueryingAndLifeCycleAbstract<Account,String> 
         implements AccountService {
	
	//SPI to inject:
	private AccountLoader accountLoader;
	private AccountSaver accountSaver;
	

	public AccountServiceImpl(AccountLoader accountLoader , AccountSaver accountSaver) {
		super(accountLoader,accountSaver);
		// constructor for SPI dependencies injections 
		this.accountLoader=accountLoader;
		this.accountSaver=accountSaver;
	}

	@Override
	public void credit(String accountId, Double amount) {
		Account account = accountLoader.loadById(accountId).get();
		account.credit(amount);
		accountSaver.updateExisting(account);
	}

	@Override
	public void debit(String accountId, Double amount) {
		Account account = accountLoader.loadById(accountId).get();
		account.debit(amount);
		accountSaver.updateExisting(account);
	}

	@Override
	public void transfer(Double amount,String sourceAccountId, String targetAccountId) {
		debit(sourceAccountId, amount);
		credit(targetAccountId, amount);
	}

	
	@Override
	public List<Account> queryAccountsByMinimunBalance(Double minimumBalance) {
		return accountLoader.loadWithMinimumBalance(minimumBalance);
	}

	@Override
	public List<Account> queryAccountsByCustomerOwnerships(String customerId) {
		return accountLoader.loadByCustomerOwnerships(customerId);
	}

	

	

}
