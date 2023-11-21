package org.mycontrib.hex.bank.init;

import org.mycontrib.hex.bank.domain.api.OperationService;
import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.api.ag.CustomerService;
import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.bank.domain.entity.ContactDetails;
import org.mycontrib.hex.bank.domain.entity.Customer;
import org.mycontrib.hex.bank.domain.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/*
 * classe utilitaire
 * qui initialise un jeu de données
 * au démarrage de l'application
 * Utile en mode développement si mode 
 * spring.jpa.hibernate.ddl-auto=create-drop
 */

@Component
@Profile("init")
public class InitDataSet {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private OperationService operationService;
	
	
	@PostConstruct
	public void initData() {
		
		Customer c1 = new Customer(null,"jean","bon","pwd1");
		c1.setContactDetails(new ContactDetails("jean.bon@worldcompany.com","0601020304", "12 rue elle 75001 Paris"));
		customerService.create(c1);
		
		Customer c2 = new Customer(null,"axelle","aire","pwd2");
		c2.setContactDetails(new ContactDetails("axelle.aire@bestcompany.com","0604030201", "2 rue quiVaBien 69001 Lyon"));
		customerService.create(c2);
		
		Account compteAa = new Account(null,"compte_Aa" , 70.0);
		compteAa = accountService.create(compteAa);
		//System.out.println("compteAa="+compteAa);
		
		Operation opAa0 = new Operation(null,-38.7 ,"achat Aa0","2023-09-25");
		opAa0 = operationService.registerOperationForAccount(opAa0, compteAa.getId());
		
		Operation opAa1 = new Operation(null,-5.9 ,"achat Aa1","2023-10-23");
		opAa1 = operationService.registerOperationForAccount(opAa1, compteAa.getId());
		//System.out.println("opAa1="+opAa1);
		Operation opAa2 = new Operation(null,-3.9 ,"achat Aa2","2023-11-06");
		opAa2 = operationService.registerOperationForAccount(opAa2, compteAa.getId());
		
		Account compteBbb = new Account(null,"compte_Bbb" , 80.0);
		compteBbb = accountService.create(compteBbb);
		
		Operation opBbb1 = new Operation(null,-8.9 ,"achat bbb1","2023-10-12");
		opBbb1 = operationService.registerOperationForAccount(opBbb1, compteBbb.getId());
	
    	
	}

}