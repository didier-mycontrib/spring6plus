package org.mycontrib.hex.bank.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.bank.domain.spi.CustomerLoader;
import org.mycontrib.hex.bank.persistence.dao.CustomerJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//@Transactional
public class CustomerLoaderAdapter implements CustomerLoader {
	
	@Autowired
	private CustomerJpaRepository customerRepository;
	


	@Override
	public Optional<Customer> loadById(String id,String... wishedDetails) {
		CustomerEntity customerEntity = customerRepository.findById(Long.parseLong(id)).orElse(null);
		return Optional.of(
				  wishedDetails.length>0
				  ?EntityConverter.INSTANCE.customerEntityToCustomer(customerEntity)
				  :EntityConverter.INSTANCE.customerEntityToCustomerWithoutDetails(customerEntity)
				  );
	}

	@Override
	public boolean existsWithThisId(String id) {
		return customerRepository.existsById(Long.parseLong(id));
	}

	@Override
	public List<Customer> loadAll() {
		//return EntityConverter.INSTANCE.customerEntityListToCustomerListWithoutDetails(customerRepository.findAll());
		
		//via allCustomers() (via @NamedNativeQuery)
		//return EntityConverter.INSTANCE.map(customerRepository.allCustomers(),Customer.class);
		return EntityConverter.INSTANCE.customerEntityListToCustomerListWithoutDetails(customerRepository.allCustomers());
		//With some native query JPA/Hibernate still loading some lazy related components !!!!
	}

	

	@Override
	public List<Customer> loadByFirstnameAndLastname(String firstname, String lastname) {
		List<CustomerEntity> listCustomerEntities = customerRepository.getByFirstnameAndLastname(firstname, lastname);
		return EntityConverter.INSTANCE.map(listCustomerEntities,Customer.class);
	}

}
