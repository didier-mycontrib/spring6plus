package org.mycontrib.hex.bank.persistence.adapter;

import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.bank.domain.spi.CustomerSaver;
import org.mycontrib.hex.bank.persistence.dao.CustomerJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.ContactDetailsEntity;
import org.mycontrib.hex.bank.persistence.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CustomerSaverAdapter implements CustomerSaver{
	
	@Autowired
	private CustomerJpaRepository customerRepository;

	@Override
	public Customer saveNew(Customer customer) {
		CustomerEntity customerEntity = EntityConverter.INSTANCE.customerToCustomerEntity(customer);
		ContactDetailsEntity contactDetailsEntity = customerEntity.getContactDetails();
		if(contactDetailsEntity!=null)
			contactDetailsEntity.setCustomer(customerEntity); //bi-directional
		customerRepository.save(customerEntity);//
		
		customer.setId(String.valueOf(customerEntity.getId()));
		return customer;
	}
	
	@Override
	public void updateExisting(Customer customer) {
		CustomerEntity customerEntity = EntityConverter.INSTANCE.customerToCustomerEntity(customer);
		customerRepository.save(customerEntity);
	}

	@Override
	public void deleteFromId(String id) {
		customerRepository.deleteById(Long.parseLong(id));
	}

	@Override
	public void remove(Customer entity) {
		customerRepository.delete(EntityConverter.INSTANCE.map(entity,CustomerEntity.class));
	}


}
