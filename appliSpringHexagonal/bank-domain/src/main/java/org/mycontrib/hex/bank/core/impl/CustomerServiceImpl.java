package org.mycontrib.hex.bank.core.impl;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.core.api.ag.CustomerService;
import org.mycontrib.hex.bank.core.domain.entity.Account;
import org.mycontrib.hex.bank.core.domain.entity.Customer;
import org.mycontrib.hex.bank.core.spi.CustomerLoader;
import org.mycontrib.hex.bank.core.spi.CustomerSaver;
import org.mycontrib.hex.generic.core.exception.ConflictDomainException;
import org.mycontrib.hex.generic.core.exception.NotFoundDomainException;
import org.mycontrib.hex.generic.core.impl.GenericQueryingAndLifeCycleAbstract;

//@Service and @Transactional will be done outside this agnostic code
public class CustomerServiceImpl 
 extends GenericQueryingAndLifeCycleAbstract<Customer,String>
   implements CustomerService{
	
	//SPI to inject:
	private CustomerLoader customerLoader;
	private CustomerSaver customerSaver;
	

	public CustomerServiceImpl(CustomerLoader customerLoader , CustomerSaver customerSaver) {
		super(customerLoader,customerSaver);
		// constructor for SPI dependencies injections 
		this.customerLoader=customerLoader;
		this.customerSaver=customerSaver;
	}



	@Override
	public List<Customer> queryByFirstnameAndLastname(String firstName, String lastName) {
		return customerLoader.loadByFirstnameAndLastname(firstName, lastName);
	}

	

}
