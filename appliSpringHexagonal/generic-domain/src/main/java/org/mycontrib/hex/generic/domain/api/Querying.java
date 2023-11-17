package org.mycontrib.hex.generic.domain.api;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

public interface Querying<T,ID> {
	Optional<T> queryById(ID id);
	T getById(ID id) throws NotFoundDomainException;
	List<T> queryAll();
}
