package org.mycontrib.hex.generic.domain.api;

import java.io.Serializable;

import org.mycontrib.hex.generic.domain.entity.WithId;
import org.mycontrib.hex.generic.domain.exception.ConflictDomainException;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

public interface DomainLifeCycle<T extends WithId<ID>,ID extends Serializable> {
	T create(T entity) throws ConflictDomainException;
	void update(T entity) throws NotFoundDomainException;
	void deleteById(ID id)throws NotFoundDomainException;
	void deleteIfExistsById(ID id);
	void remove(T entity);
}
