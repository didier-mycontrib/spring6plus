package org.mycontrib.hex.bank.domain.api;

import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.generic.domain.api.DomainLifeCycle;
import org.mycontrib.hex.generic.domain.exception.ConflictDomainException;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

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
