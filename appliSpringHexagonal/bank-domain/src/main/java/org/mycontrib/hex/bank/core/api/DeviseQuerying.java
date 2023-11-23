package org.mycontrib.hex.bank.core.api;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.api.Querying;

public interface DeviseQuerying extends Querying<Devise,String>{
	/*
	 inherited methods:
	 
	 Optional<T> queryById(ID id,String... wishedDetails);
	 T getById(ID id,String... wishedDetails) throws NotFoundDomainException;
	 List<T> queryAll();
	 */
	
}
