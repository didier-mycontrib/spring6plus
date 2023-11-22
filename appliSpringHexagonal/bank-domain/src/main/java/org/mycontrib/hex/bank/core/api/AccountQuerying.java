package org.mycontrib.hex.bank.core.api;

import java.util.List;

import org.mycontrib.hex.bank.core.domain.entity.Account;
import org.mycontrib.hex.bank.core.domain.entity.Operation;
import org.mycontrib.hex.generic.core.api.Querying;

public interface AccountQuerying extends Querying<Account,String>{
	/*
	 inherited methods:
	 
	 Optional<T> queryById(ID id,String... wishedDetails);
	 T getById(ID id,String... wishedDetails) throws NotFoundDomainException;
	 List<T> queryAll();
	 */
	
	List<Account> queryAccountsByMinimunBalance(Double minimumBalance);
	List<Account> queryAccountsByCustomerOwnerships(String customerId);
	
}
