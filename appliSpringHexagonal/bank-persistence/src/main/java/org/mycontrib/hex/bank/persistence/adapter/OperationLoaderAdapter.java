package org.mycontrib.hex.bank.persistence.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mycontrib.hex.bank.core.domain.entity.Operation;
import org.mycontrib.hex.bank.core.spi.OperationLoader;
import org.mycontrib.hex.bank.persistence.dao.OperationJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
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
		List<OperationEntity> opEntities = null;
		//opEntities = operationRepository.findAll();
		if(accountId==null) return new ArrayList<Operation>();
		Long lAccountId = EntityConverter.INSTANCE.stringToLong(accountId);
		
		if(beginDate==null) {
			opEntities = operationRepository.findByAccountId(lAccountId);
		}
		else {
			LocalDateTime ldtBeginDate  = (LocalDate.parse(beginDate)).atStartOfDay();
			LocalDateTime ldtEndDate  = LocalDateTime.now(); //by default
			if(endDate!=null)
			    ldtEndDate  = (LocalDate.parse(endDate)).atTime(23, 59);
			//opEntities = operationRepository.findByTimestampBetween(ldtBeginDate,ldtEndDate);
			opEntities = operationRepository.findByAccountIdAndTimestampBetween(lAccountId,ldtBeginDate,ldtEndDate);
		}
		return EntityConverter.INSTANCE.operationEntityListToOperationListWithoutDetails(opEntities);
	}

}
