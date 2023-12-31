package org.mycontrib.hex.bank.core.spi;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.core.domain.entity.Account;
import org.mycontrib.hex.generic.core.spi.Loader;

//Query (readOnly) part of persistence
public interface AccountLoader extends Loader<Account,String>{
	/*
	 inherited methods:
	 
	 public Optional<T> loadById(ID id,String... wishedDetails);
	 public boolean existsWithThisId(ID id);
	 public List<T> loadAll();
	 */
	
	public List<Account> loadWithMinimumBalance(Double minBalance);
	public List<Account> loadByCustomerOwnerships(String customerId);
}
