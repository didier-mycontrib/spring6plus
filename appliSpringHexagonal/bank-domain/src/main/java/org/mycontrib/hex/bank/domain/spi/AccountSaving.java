package org.mycontrib.hex.bank.domain.spi;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.generic.domain.spi.Saving;

//other part of persistence (no readOnly)
public interface AccountSaving extends Saving<Account,String>{
	/*
	 inherited methods:
	 
	 public T saveOrUpdate(T entity);
	 public void deleteFromId(ID id);
	 public void remove(T entity);
	 */

}
