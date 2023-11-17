package org.mycontrib.hex.bank.init;

import org.mycontrib.hex.bank.domain.api.ag.AccountService;
import org.mycontrib.hex.bank.domain.entity.Account;
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
	
	
	@PostConstruct
	public void initData() {
		
		Account compteAa = new Account(null,"compte_Aa" , 70.0);
		compteAa = accountService.create(compteAa);
		
		
		Account compteBbb = new Account(null,"compte_Bbb" , 80.0);
		compteBbb = accountService.create(compteBbb);
	
    	
	}

}
