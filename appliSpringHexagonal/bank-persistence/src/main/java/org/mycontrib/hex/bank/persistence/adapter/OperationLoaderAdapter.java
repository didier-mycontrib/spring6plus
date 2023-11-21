package org.mycontrib.hex.bank.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mycontrib.hex.bank.domain.entity.Operation;
import org.mycontrib.hex.bank.domain.spi.OperationLoader;
import org.mycontrib.hex.bank.persistence.dao.OperationJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationLoaderAdapter implements OperationLoader {
	
	@Autowired
	private OperationJpaRepository operationRepository;

	@Override
	public Optional<Operation> loadById(String id) {
		if(id==null)return Optional.empty();
		UUID uuid = EntityConverter.INSTANCE.stringToUuid(id);
		OperationEntity opEntity = operationRepository.findById(uuid).orElse(null);
		return Optional.ofNullable( EntityConverter.INSTANCE.operationEntityToOperation(opEntity));
	}

	@Override
	public boolean existsWithThisId(String id) {
		if(id==null)return false;
		UUID uuid = EntityConverter.INSTANCE.stringToUuid(id);
		return operationRepository.existsById(uuid);
	}

	@Override
	public List<Operation> loadByAccountAndPeriod(String accountId, String beginDate, String endDate) {
		List<OperationEntity> opEntities = operationRepository.findAll();
		return EntityConverter.INSTANCE.map(opEntities, Operation.class);
	}

}
