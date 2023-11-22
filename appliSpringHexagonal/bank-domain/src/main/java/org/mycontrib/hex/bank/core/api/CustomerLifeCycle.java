package org.mycontrib.hex.bank.core.api;

import org.mycontrib.hex.bank.core.domain.entity.Customer;
import org.mycontrib.hex.generic.core.api.DomainLifeCycle;
import org.mycontrib.hex.generic.core.exception.ConflictDomainException;
import org.mycontrib.hex.generic.core.exception.NotFoundDomainException;

public interface CustomerLifeCycle extends DomainLifeCycle<Customer,String>{
	/*
	 inherited methods:
	 
	 T create(T entity) throws ConflictDomainException;
	 void update(T entity) throws NotFoundDomainException;
	 void deleteById(ID id)throws NotFoundDomainException;
	 void deleteIfExistsById(ID id);
	 void remove(T entity);
	
	 */
}
