package org.mycontrib.hex.generic.domain.impl;

import java.io.Serializable;

import org.mycontrib.hex.generic.domain.api.DomainLifeCycle;
import org.mycontrib.hex.generic.domain.entity.WithId;
import org.mycontrib.hex.generic.domain.exception.ConflictDomainException;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;
import org.mycontrib.hex.generic.domain.spi.Loader;
import org.mycontrib.hex.generic.domain.spi.Saver;

public abstract class GenericQueryingAndLifeCycleAbstract<T extends WithId<ID>,ID extends Serializable>
                      extends GenericQueringAbstract<T, ID> implements DomainLifeCycle<T,ID>{
    private Saver<T,ID> genericSaver;
	
	public void setGenericSaver(Saver<T,ID> genericSaver) {
		this.genericSaver=genericSaver;
	}
	

	public GenericQueryingAndLifeCycleAbstract(Loader<T, ID> genericLoader, Saver<T, ID> genericSaver) {
		super(genericLoader);
		this.genericSaver = genericSaver;
	}


	public T create(T entity) throws ConflictDomainException {
		if(entity==null) return null;
		ID id = entity.extractId();
		if(id!=null && genericLoader.existsWithThisId(id))
			throw new ConflictDomainException("conflict with an existing entity with same id="+id + " ; cannot create");
		return genericSaver.saveNew(entity);
	}


	public void update(T entity) throws NotFoundDomainException {
		if(entity==null) return ;
		ID id = entity.extractId();
		if(id==null || !genericLoader.existsWithThisId(id))
			throw new NotFoundDomainException("no existing entity found with id="+id + " ; cannot update");
		genericSaver.updateExisting(entity);
	}


	public void deleteById(ID id) throws NotFoundDomainException {
		if(id==null || !genericLoader.existsWithThisId(id))
			throw new NotFoundDomainException("no existing entity found with id="+id + " ; cannot delete");
		genericSaver.deleteFromId(id);
	}

	
	public void deleteIfExistsById(ID id) {
		if(id!=null && genericLoader.existsWithThisId(id))
			genericSaver.deleteFromId(id);
	}

	
	public void remove(T entity) {
		genericSaver.remove(entity);
	}
}
