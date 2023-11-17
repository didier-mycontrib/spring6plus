package org.mycontrib.hex.generic.domain.spi;

public interface Saving<T,ID> {
	public T saveOrUpdate(T entity);
	public void deleteFromId(ID id);

}
