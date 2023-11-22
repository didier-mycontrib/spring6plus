package org.mycontrib.hex.bank.core.spi;

import java.util.List;

import org.mycontrib.hex.bank.core.domain.entity.Customer;
import org.mycontrib.hex.generic.core.spi.Loader;

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
