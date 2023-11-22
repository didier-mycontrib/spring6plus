package org.mycontrib.hex.bank.persistence.adapter;

import java.time.LocalDate;
import java.util.List;

import org.mycontrib.hex.bank.core.domain.entity.Account;
import org.mycontrib.hex.bank.core.domain.entity.AccountOwnership;
import org.mycontrib.hex.bank.core.spi.AccountSaver;
import org.mycontrib.hex.bank.persistence.dao.AccountJpaRepository;
import org.mycontrib.hex.bank.persistence.dao.AccountOwnershipJpaRepository;
import org.mycontrib.hex.bank.persistence.dao.CustomerJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.AccountEntity;
import org.mycontrib.hex.bank.persistence.entity.AccountOwnershipEntity;
import org.mycontrib.hex.bank.persistence.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountSaverAdapter implements AccountSaver {
	
	@Autowired
	private AccountJpaRepository accountRepository;
	
	@Autowired
	private CustomerJpaRepository customerRepository;
	
	@Autowired
	private AccountOwnershipJpaRepository accountOwnershipRepository;

	@Override
	public Account saveNew(Account account) {
		AccountEntity accountEntity = EntityConverter.INSTANCE.accountToAccountEntity(account);
		accountEntity = accountRepository.save(accountEntity);
		
		List<AccountOwnership> accountOwnerships= account.getAccountOwnerships();
		if(accountOwnerships!=null && !accountOwnerships.isEmpty()) {
			for(AccountOwnership accountOwnership : accountOwnerships) {
				//System.out.println("accountOwnership="+accountOwnership);
				//System.out.println("\t accountEntity="+accountEntity);
				CustomerEntity customerEntity =  customerRepository.findById(Long.parseLong( accountOwnership.getCustomerId())).get();
				//System.out.println("\t customerEntity="+customerEntity);
				AccountOwnershipEntity accountOwnershipEntity = 
						new AccountOwnershipEntity(accountEntity,customerEntity,
													LocalDate.parse(accountOwnership.getBeginDate()));
				accountOwnershipRepository.save(accountOwnershipEntity);
			}
		}
		
		account.setId(String.valueOf(accountEntity.getId()));
		return account;
	}
	
	@Override
	public void updateExisting(Account account) {
		AccountEntity accountEntity = EntityConverter.INSTANCE.accountToAccountEntity(account);
		accountRepository.save(accountEntity);
	}

	@Override
	public void deleteFromId(String id) {
		accountRepository.deleteById(Long.parseLong(id));
	}

	@Override
	public void remove(Account entity) {
		accountRepository.delete(EntityConverter.INSTANCE.map(entity,AccountEntity.class));
	}

}
