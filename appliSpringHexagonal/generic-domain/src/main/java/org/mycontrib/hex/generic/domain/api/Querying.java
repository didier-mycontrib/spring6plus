package org.mycontrib.hex.generic.domain.api;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.generic.domain.entity.WithId;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

public interface Querying<T extends WithId<ID>,ID extends Serializable> {
	Optional<T> queryById(ID id,String... wishedDetails);//wishedDetails may be empty or names of wished/asked details
	T getById(ID id,String... wishedDetails) throws NotFoundDomainException;
	List<T> queryAll();
}
