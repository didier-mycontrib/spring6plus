package org.mycontrib.hex.bank.core.spi;

import org.mycontrib.hex.bank.core.domain.entity.Operation;

public interface OperationSaver {
	 public Operation saveNew(Operation entity);
}
