package org.mycontrib.hex.generic.domain.api;

import org.mycontrib.hex.generic.domain.exception.ConflictDomainException;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

public interface DomainLifeCycle<T,ID> {
	T create(T entity) throws ConflictDomainException;
	T update(T entity) throws NotFoundDomainException;
	void deleteById(ID id)throws NotFoundDomainException;
	void deleteIfExistsById(ID id);
	void remove(T entity);
}
