package org.mycontrib.hex.generic.core.domain.entity;

import java.io.Serializable;

public interface WithId<ID extends Serializable> {
	
	public ID extractId(); 

}
