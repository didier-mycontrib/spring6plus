package org.mycontrib.hex.bank.domain.api;

import java.util.List;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.entity.Operation;
import org.mycontrib.hex.generic.domain.api.Querying;

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
