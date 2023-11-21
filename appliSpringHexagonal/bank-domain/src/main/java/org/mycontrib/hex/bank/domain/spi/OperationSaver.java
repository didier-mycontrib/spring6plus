package org.mycontrib.hex.bank.domain.spi;

import org.mycontrib.hex.bank.domain.entity.Operation;

public interface OperationSaver {
	 public Operation saveNew(Operation entity);
}
