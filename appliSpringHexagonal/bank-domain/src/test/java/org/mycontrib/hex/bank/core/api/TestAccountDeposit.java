package org.mycontrib.hex.bank.core.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mycontrib.hex.bank.core.domain.entity.Account;
import org.mycontrib.hex.bank.core.impl.AccountServiceImpl;
import org.mycontrib.hex.bank.core.spi.AccountLoader;
import org.mycontrib.hex.bank.core.spi.AccountSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class) //for JUnit 5
public class TestAccountDeposit {
	
	private static Logger logger = LoggerFactory.getLogger(TestAccountDeposit.class);


	//API to test
    @InjectMocks
	private AccountServiceImpl depositAction;//DepositAction = interface of AccountServiceImpl to test;
	
	//SPI to Mock:
	@Mock
	private AccountLoader accountLoader;//to mock;
	@Mock
	private AccountSaver accountSaver;//to mock;
	
	@Test
	public void testDeposit() {
		String id="1";
		double initialBalance=100.0;
		double depositAmount=50.0;
		double expectedNewBalance=initialBalance+depositAmount;//150.0
		Mockito.when(accountLoader.loadById(id))
		       .thenReturn(Optional.of(new Account(id,"compte qui va bien",initialBalance)));
		
		//Do deposit
		Account account = accountLoader.loadById(id).get();
		//logger.debug("account before deposit: "+ account);
		System.out.println("account before deposit: "+ account);
		
		depositAction.credit(account.getId(), depositAmount);
		
		//Verify new balance in memory:
		//logger.debug("account after deposit: "+ account);
		System.out.println("account after deposit: "+ account);
		assertEquals(account.getBalance(),expectedNewBalance,0.00001);
		
		//Verify that new balance saving was asked to accountSaver:
		Mockito.verify(accountSaver)
		       .updateExisting(
				   Mockito.eq( new Account(id,"compte qui va bien",expectedNewBalance) ) /* rely on Account.equals())*/
		        );
	}

}
