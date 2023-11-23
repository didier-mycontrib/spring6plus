package org.mycontrib.hex.bank.core.spi;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.spi.Loader;

//Query (readOnly) part of persistence
public interface DeviseLoader extends Loader<Devise,String>{
	/*
	 inherited methods:
	 
	 public Optional<T> loadById(ID id,String... wishedDetails);
	 public boolean existsWithThisId(ID id);
	 public List<T> loadAll();
	 */
}
