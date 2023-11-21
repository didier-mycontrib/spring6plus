package org.mycontrib.hex.bank.persistence.adapter;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.entity.ContactDetails;
import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.bank.domain.entity.Operation;
import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.mycontrib.hex.bank.persistence.entity.ContactDetailsEntity;
import org.mycontrib.hex.bank.persistence.entity.CustomerEntity;
import org.mycontrib.hex.bank.persistence.entity.OperationEntity;
import org.mycontrib.hex.generic.util.converter.AbstractGenericConverter;

public class EntityConverter extends AbstractGenericConverter {
	
	public static EntityConverter INSTANCE = new EntityConverter();
	
	public AccountEntity accountToAccountEntity(Account a) {
		return new AccountEntity(stringToLong(a.getId()),a.getLabel(),a.getBalance());
	}
	
	public OperationEntity operationToOperationEntity(Operation o) {
		OperationEntity oe= new OperationEntity(stringToUuid(o.getId()),o.getAmount(),o.getLabel(),o.getTimestamp());
		Account account = o.getAccount();
		if(account!=null) {
			oe.setAccount(accountToAccountEntity(account));
		}
		return oe;
	}
	
	public Operation operationEntityToOperation(OperationEntity oe) {
		Operation o= new Operation(uuidToString(oe.getId()),oe.getAmount(),oe.getLabel(),oe.getTimestamp());
		AccountEntity accountEntity = oe.getAccount();
		if(accountEntity!=null) {
			o.setAccount(accountEntityToAccount(accountEntity));
		}
		return o;
	}
	
	public Account accountEntityToAccount(AccountEntity a) {
		return new Account(longToString(a.getId()),a.getLabel(),a.getBalance());
	}
	
	public CustomerEntity customerToCustomerEntity(Customer c ) {
		CustomerEntity ce =  new CustomerEntity(stringToLong(c.getId()),c.getFirstname(),c.getLastname(),c.getPassword());
		ContactDetails cd = c.getContactDetails();
		if(cd!=null) ce.setContactDetails(new ContactDetailsEntity(cd.getEmail(),cd.getMobile(),cd.getMainAddress()));
		return ce;
	}
	
	public Customer customerEntityToCustomer(CustomerEntity ce) {
		Customer c =  new Customer(longToString(ce.getId()),ce.getFirstname(),ce.getLastname(),ce.getPassword());
		ContactDetailsEntity cdd = ce.getContactDetails();
		if(cdd!=null) c.setContactDetails(new ContactDetails(cdd.getEmail(),cdd.getMobile(),cdd.getMainAddress()));
		return c;
	}
	

	@Override
	public Object getSpecificConverter() {
		return INSTANCE;
	}
}
