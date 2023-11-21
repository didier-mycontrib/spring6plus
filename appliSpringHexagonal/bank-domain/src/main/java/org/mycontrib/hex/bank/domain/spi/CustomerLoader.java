package org.mycontrib.hex.bank.domain.spi;

import java.util.List;

import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.generic.domain.spi.Loader;

//Query (readOnly) part of persistence
public interface CustomerLoader extends Loader<Customer,String>{
	/*
	 inherited methods:
	 
	 public Optional<T> loadById(ID id,String... wishedDetails);
	 public boolean existsWithThisId(ID id);
	 public List<T> loadAll();
	 */
	
	public List<Customer> loadByFirstnameAndLastname(String firstname, String lastname);
}
