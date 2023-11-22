package org.mycontrib.hex.bank.core.api;

import java.util.List;

import org.mycontrib.hex.bank.core.domain.entity.Customer;
import org.mycontrib.hex.generic.core.api.Querying;

public interface CustomerQuerying extends Querying<Customer,String>{
	/*
	 inherited methods:
	 
	 Optional<T> queryById(ID id,String... wishedDetails);
	 T getById(ID id,String... wishedDetails) throws NotFoundDomainException;
	 List<T> queryAll();
	 */
	
	List<Customer> queryByFirstnameAndLastname(String firstName, String lastName);
	
}
