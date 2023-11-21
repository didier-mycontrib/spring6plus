package org.mycontrib.hex.bank.domain.spi;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.generic.domain.spi.Loader;

//Query (readOnly) part of persistence
public interface AccountLoader extends Loader<Account,String>{
	/*
	 inherited methods:
	 
	 public Optional<T> loadById(ID id,String... wishedDetails);
	 public boolean existsWithThisId(ID id);
	 public List<T> loadAll();
	 */
	
	public List<Account> loadWithMinimumBalance(Double minBalance);
}
