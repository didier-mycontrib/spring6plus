package org.mycontrib.hex.generic.domain.spi;

public interface Saver<T,ID> {
	public T saveNew(T entity);
	public void  updateExisting(T entity);
	public void deleteFromId(ID id);
	public void remove(T entity);
}
