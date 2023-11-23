package org.mycontrib.hex.bank.core.spi;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.spi.Saver;

//other part of persistence (no readOnly)
public interface DeviseSaver extends Saver<Devise,String>{
	/*
	 inherited methods:
	 
	 public T saveNew(T entity);
	 public void  updateExisting(T entity);
	 public void deleteFromId(ID id);
	 public void remove(T entity);
	 */

}
