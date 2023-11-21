package org.mycontrib.hex.bank.domain.spi;

import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.generic.domain.spi.Saver;

//other part of persistence (no readOnly)
public interface CustomerSaver extends Saver<Customer,String>{
	/*
	 inherited methods:
	 
	 public T saveNew(T entity);
	 public void  updateExisting(T entity);
	 public void deleteFromId(ID id);
	 public void remove(T entity);
	 */

}
