package org.mycontrib.hex.generic.domain.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.generic.domain.api.Querying;
import org.mycontrib.hex.generic.domain.entity.WithId;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;
import org.mycontrib.hex.generic.domain.spi.Loader;

public abstract class GenericQueringAbstract <T extends WithId<ID>,ID extends Serializable> implements Querying<T,ID> {
	
	protected Loader<T,ID> genericLoader;
	
	public void setGenericLoader(Loader<T,ID> genericLoader) {
		this.genericLoader=genericLoader;
	}
	
	

	public GenericQueringAbstract(Loader<T, ID> genericLoader) {
		this.genericLoader = genericLoader;
	}


	
	
	public Optional<T> queryById(ID id,String... wishedDetails) {
		return genericLoader.loadById(id,wishedDetails);
	}

	public T getById(ID id,String... wishedDetails) throws NotFoundDomainException {
		try {
			return genericLoader.loadById(id,wishedDetails).get();
		} catch (Exception e) {
			// logger...
			throw new NotFoundDomainException("no entity found with id="+id);
		}
	}

	public List<T> queryAll() {
		return genericLoader.loadAll();
	}
}
