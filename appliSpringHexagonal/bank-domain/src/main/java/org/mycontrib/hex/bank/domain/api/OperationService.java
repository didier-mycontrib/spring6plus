package org.mycontrib.hex.bank.domain.api;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.domain.entity.Operation;
import org.mycontrib.hex.generic.domain.exception.NotFoundDomainException;

public interface OperationService {
	public Operation registerOperationForAccount(Operation op, String accountId);
	public Optional<Operation> queryById(String operationId);
	public Operation getById(String operationId) throws NotFoundDomainException;
	public List<Operation> queryOperationsForAccount(String accountId,String firstDate, String lastDate);
}
