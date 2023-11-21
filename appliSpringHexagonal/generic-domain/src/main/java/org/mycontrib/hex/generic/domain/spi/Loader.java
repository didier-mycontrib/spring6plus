package org.mycontrib.hex.generic.domain.spi;

import java.util.List;
import java.util.Optional;

public  interface Loader<T,ID> {
	public Optional<T> loadById(ID id,String... wishedDetails);
	public boolean existsWithThisId(ID id);
	public List<T> loadAll();
}
