package org.mycontrib.hex.bank.conf;

import org.mycontrib.hex.bank.core.api.OperationService;
import org.mycontrib.hex.bank.core.api.ag.AccountService;
import org.mycontrib.hex.bank.core.api.ag.CustomerService;
import org.mycontrib.hex.bank.core.impl.AccountServiceImpl;
import org.mycontrib.hex.bank.core.impl.CustomerServiceImpl;
import org.mycontrib.hex.bank.core.impl.OperationServiceImpl;
import org.mycontrib.hex.bank.core.spi.AccountLoader;
import org.mycontrib.hex.bank.core.spi.AccountSaver;
import org.mycontrib.hex.bank.core.spi.CustomerLoader;
import org.mycontrib.hex.bank.core.spi.CustomerSaver;
import org.mycontrib.hex.bank.core.spi.OperationLoader;
import org.mycontrib.hex.bank.core.spi.OperationSaver;
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
