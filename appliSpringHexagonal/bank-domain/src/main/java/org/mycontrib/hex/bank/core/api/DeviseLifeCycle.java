package org.mycontrib.hex.bank.core.api;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.api.DomainLifeCycle;

public interface DeviseLifeCycle extends DomainLifeCycle<Devise,String>{
	/*
	 inherited methods:
	 
	 T create(T entity) throws ConflictDomainException;
	 void update(T entity) throws NotFoundDomainException;
	 void deleteById(ID id)throws NotFoundDomainException;
	 void deleteIfExistsById(ID id);
	 void remove(T entity);
	
	 */
}
