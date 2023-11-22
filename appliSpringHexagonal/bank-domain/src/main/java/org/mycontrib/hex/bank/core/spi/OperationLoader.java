package org.mycontrib.hex.bank.core.spi;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.core.domain.entity.Operation;

public interface OperationLoader {
	
	 public Optional<Operation> loadById(String id);
	 public boolean existsWithThisId(String id);
	 public List<Operation> loadByAccountAndPeriod(String accountId,String beginDate,String endDate);

}
