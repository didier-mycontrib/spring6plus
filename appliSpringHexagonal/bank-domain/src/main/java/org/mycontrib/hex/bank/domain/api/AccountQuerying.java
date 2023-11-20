package org.mycontrib.hex.bank.domain.api;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.generic.domain.api.Querying;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

public interface AccountQuerying extends Querying<Account,String>{
	/*
	 inherited methods:
	 
	 Optional<T> queryById(ID id);
	 T getById(ID id) throws NotFoundDomainException;
	 List<T> queryAll();
	 */
	
	List<Account> queryAccountsByMinimunBalance(Double minimumBalance);
	
}
