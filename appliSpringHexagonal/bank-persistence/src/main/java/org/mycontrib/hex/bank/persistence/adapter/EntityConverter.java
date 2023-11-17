package org.mycontrib.hex.bank.persistence.adapter;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.mycontrib.hex.generic.util.converter.AbstractGenericConverter;

public class EntityConverter extends AbstractGenericConverter {
	
	public static EntityConverter INSTANCE = new EntityConverter();
	
	public AccountEntity accountToAccountEntity(Account a) {
		return new AccountEntity(stringToLong(a.getId()),a.getLabel(),a.getBalance());
	}
	
	public Account accountEntityToAccount(AccountEntity a) {
		return new Account(longToString(a.getId()),a.getLabel(),a.getBalance());
	}

	@Override
	public Object getSpecificConverter() {
		return INSTANCE;
	}
}
