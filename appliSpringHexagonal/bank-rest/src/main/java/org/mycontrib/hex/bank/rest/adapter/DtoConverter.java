package org.mycontrib.hex.bank.rest.adapter;

import org.mycontrib.hex.generic.util.converter.AbstractGenericConverter;

public class DtoConverter extends AbstractGenericConverter {
	
	public static DtoConverter INSTANCE = new DtoConverter();
	
	//V1 : automatic conversion via inherited code

	@Override
	public Object getSpecificConverter() {
		return INSTANCE;
	}
}
