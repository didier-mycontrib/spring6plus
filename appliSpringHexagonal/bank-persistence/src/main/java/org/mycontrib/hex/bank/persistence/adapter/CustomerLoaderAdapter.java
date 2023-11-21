package org.mycontrib.hex.bank.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.bank.domain.spi.CustomerLoader;
import org.mycontrib.hex.bank.persistence.dao.CustomerJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerLoaderAdapter implements CustomerLoader {
	
	@Autowired
	private CustomerJpaRepository customerRepository;
	
	public CustomerEntity withOrWithoutDetails(CustomerEntity customerEntity,String... wishedDetails) {
		if(customerEntity==null)
			return null;
		if(wishedDetails.length==0)
			customerEntity.setContactDetails(null);
		return customerEntity;
	}
	
	public List<CustomerEntity> withOrWithoutDetails(List<CustomerEntity> customerEntities,String... wishedDetails) {
		return customerEntities.stream()
				.map((c)->withOrWithoutDetails(c,wishedDetails))
				.toList();
	}


	@Override
	public Optional<Customer> loadById(String id,String... wishedDetails) {
		return Optional.of(
				  EntityConverter.INSTANCE.customerEntityToCustomer(
						  withOrWithoutDetails(customerRepository.findById(Long.parseLong(id)).orElse(null),wishedDetails)
				      )
				  );
	}

	@Override
	public boolean existsWithThisId(String id) {
		return customerRepository.existsById(Long.parseLong(id));
	}

	@Override
	public List<Customer> loadAll() {
		return EntityConverter.INSTANCE.map(withOrWithoutDetails(customerRepository.findAll()),
				Customer.class);
	}

	

	@Override
	public List<Customer> loadByFirstnameAndLastname(String firstname, String lastname) {
		List<CustomerEntity> listCustomerEntities = customerRepository.getByFirstnameAndLastname(firstname, lastname);
		return EntityConverter.INSTANCE.map(listCustomerEntities,Customer.class);
	}

}
