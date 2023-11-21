package org.mycontrib.hex.bank.domain.spi;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.generic.domain.spi.Saver;

//other part of persistence (no readOnly)
public interface AccountSaver extends Saver<Account,String>{
	/*
	 inherited methods:
	 
	 public T saveNew(T entity);
	 public void  updateExisting(T entity);
	 public void deleteFromId(ID id);
	 public void remove(T entity);
	 */

}
