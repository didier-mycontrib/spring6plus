package org.mycontrib.hex.bank.core.impl;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.core.api.OperationService;
import org.mycontrib.hex.bank.core.domain.entity.Account;
import org.mycontrib.hex.bank.core.domain.entity.Operation;
import org.mycontrib.hex.bank.core.spi.AccountLoader;
import org.mycontrib.hex.bank.core.spi.OperationLoader;
import org.mycontrib.hex.bank.core.spi.OperationSaver;
import org.mycontrib.hex.generic.core.exception.NotFoundDomainException;

//@Service and @Transactional will be done outside this agnostic code
public class OperationServiceImpl implements OperationService {
	
	//SPI to inject:
	private AccountLoader accountLoader;
	private OperationLoader operationLoader;
	private OperationSaver operationSaver;
	

	public OperationServiceImpl(AccountLoader accountLoader ,OperationLoader operationLoader,OperationSaver operationSaver) {
		// constructor for SPI dependencies injections 
		this.accountLoader=accountLoader;
		this.operationLoader=operationLoader;
		this.operationSaver=operationSaver;
	}


	@Override
	public Operation registerOperationForAccount(Operation op, String accountId) {
		Account account = accountLoader.loadById(accountId).orElse(null);
		op.setAccount(account);
		Operation savedOperation= operationSaver.saveNew(op);
		return savedOperation;
	}

	@Override
	public List<Operation> queryOperationsForAccount(String accountId, String firstDate, String lastDate) {
		return operationLoader.loadByAccountAndPeriod(accountId, firstDate, lastDate);
	}


	@Override
	public Optional<Operation> queryById(String operationId) {
		return operationLoader.loadById(operationId);
	}


	@Override
	public Operation getById(String operationId) throws NotFoundDomainException {
		Operation operation = operationLoader.loadById(operationId).orElse(null);
		if(operation==null) throw new NotFoundDomainException("no operation found with id="+operationId);
		return operation;
	}

	

}
