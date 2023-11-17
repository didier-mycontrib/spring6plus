package org.mycontrib.hex.bank.conf;

import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.impl.AccountServiceImpl;
import org.mycontrib.hex.bank.domain.spi.AccountLoading;
import org.mycontrib.hex.bank.domain.spi.AccountSaving;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankDomainConfig {
	
	@Bean
	public AccountService accountService(AccountLoading accountLoader , AccountSaving accountSaver) {
		return new AccountServiceImpl(accountLoader,accountSaver);
	}

}
