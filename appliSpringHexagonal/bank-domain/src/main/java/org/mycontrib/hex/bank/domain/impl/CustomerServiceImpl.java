package org.mycontrib.hex.bank.domain.impl;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.api.ag.CustomerService;
import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.bank.domain.spi.CustomerLoader;
import org.mycontrib.hex.bank.domain.spi.CustomerSaver;
import org.mycontrib.hex.generic.domain.exception.ConflictDomainException;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;
import org.mycontrib.hex.generic.domain.impl.GenericQueryingAndLifeCycleAbstract;

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
