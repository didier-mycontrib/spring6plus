package org.mycontrib.hex.bank.persistence.adapter;

import org.mycontrib.hex.bank.core.domain.entity.Operation;
import org.mycontrib.hex.bank.core.spi.OperationSaver;
import org.mycontrib.hex.bank.persistence.dao.OperationJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationSaverAdapter implements OperationSaver {
	
	@Autowired
	private OperationJpaRepository operationRepository;

	@Override
	public Operation saveNew(Operation op) {
		OperationEntity operationEntity = EntityConverter.INSTANCE.operationToOperationEntity(op);
		operationRepository.save(operationEntity);
		op.setId( EntityConverter.INSTANCE.uuidToString(operationEntity.getId()) );
		return op;
	}

}
