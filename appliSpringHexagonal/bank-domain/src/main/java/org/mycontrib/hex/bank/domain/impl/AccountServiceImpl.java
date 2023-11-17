package org.mycontrib.hex.bank.domain.impl;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.spi.AccountLoading;
import org.mycontrib.hex.bank.domain.spi.AccountSaving;
import org.mycontrib.hex.generic.domain.exception.ConflictDomainException;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

//@Service and @Transactional will be done outside this agnostic code
public class AccountServiceImpl implements AccountService{
	
	//SPI to inject:
	private AccountLoading accountLoader;
	private AccountSaving accountSaver;
	

	public AccountServiceImpl(AccountLoading accountLoader , AccountSaving accountSaver) {
		// constructor for SPI dependencies injections 
		this.accountLoader=accountLoader;
		this.accountSaver=accountSaver;
	}

	@Override
	public void credit(String accountId, Double amount) {
		Account account = accountLoader.loadById(accountId).get();
		account.credit(amount);
		accountSaver.saveOrUpdate(account);
	}

	@Override
	public void debit(String accountId, Double amount) {
		Account account = accountLoader.loadById(accountId).get();
		account.debit(amount);
		accountSaver.saveOrUpdate(account);
	}

	@Override
	public void transfer(String sourceAccountId, String targetAccountId, Double amount) {
		debit(sourceAccountId, amount);
		credit(targetAccountId, amount);
	}

	@Override
	public Optional<Account> queryById(String id) {
		return accountLoader.loadById(id);
	}

	@Override
	public Account getById(String id) throws NotFoundDomainException {
		try {
			return accountLoader.loadById(id).get();
		} catch (Exception e) {
			// logger...
			throw new NotFoundDomainException("no account found with id="+id);
		}
	}

	@Override
	public List<Account> queryAll() {
		return accountLoader.loadAll();
	}

	@Override
	public Account create(Account entity) throws ConflictDomainException {
		if(entity==null) return null;
		String id = entity.getId();
		if(id!=null && accountLoader.existsWithThisId(id))
			throw new ConflictDomainException("conflict with an existing account with same id="+id + " ; cannot create");
		return accountSaver.saveOrUpdate(entity);
	}

	@Override
	public Account update(Account entity) throws NotFoundDomainException {
		if(entity==null) return null;
		String id = entity.getId();
		if(id==null || !accountLoader.existsWithThisId(id))
			throw new NotFoundDomainException("no existing account found with id="+id + " ; cannot update");
		return accountSaver.saveOrUpdate(entity);
	}

	@Override
	public void deleteById(String id) throws NotFoundDomainException {
		if(id==null || !accountLoader.existsWithThisId(id))
			throw new NotFoundDomainException("no existing account found with id="+id + " ; cannot delete");
		accountSaver.deleteFromId(id);
	}

	@Override
	public void deleteIfExistsById(String id) {
		if(id!=null && accountLoader.existsWithThisId(id))
			accountSaver.deleteFromId(id);
	}

	@Override
	public void remove(Account entity) {
		// TODO Auto-generated method stub
		
	}

}
