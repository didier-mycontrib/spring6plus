package org.mycontrib.hex.bank.conf;

import org.mycontrib.hex.bank.domain.api.OperationService;
import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.api.ag.CustomerService;
import org.mycontrib.hex.bank.domain.impl.AccountServiceImpl;
import org.mycontrib.hex.bank.domain.impl.CustomerServiceImpl;
import org.mycontrib.hex.bank.domain.impl.OperationServiceImpl;
import org.mycontrib.hex.bank.domain.spi.AccountLoader;
import org.mycontrib.hex.bank.domain.spi.AccountSaver;
import org.mycontrib.hex.bank.domain.spi.CustomerLoader;
import org.mycontrib.hex.bank.domain.spi.CustomerSaver;
import org.mycontrib.hex.bank.domain.spi.OperationLoader;
import org.mycontrib.hex.bank.domain.spi.OperationSaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankDomainConfig {
	
	@Bean
	public AccountService accountService(AccountLoader accountLoader , AccountSaver accountSaver) {
		return new AccountServiceImpl(accountLoader,accountSaver);
	}
	
	@Bean
	public CustomerService customerService(CustomerLoader customerLoader , CustomerSaver customerSaver) {
		return new CustomerServiceImpl(customerLoader,customerSaver);
	}
	
	@Bean
	public OperationService operationService(AccountLoader accountLoader , 
			                                 OperationLoader operationLoader , 
			                                 OperationSaver operationSaver) {
		return new OperationServiceImpl(accountLoader,operationLoader,operationSaver );
	}

}
