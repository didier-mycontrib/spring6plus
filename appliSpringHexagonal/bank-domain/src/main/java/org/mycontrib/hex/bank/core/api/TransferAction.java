package org.mycontrib.hex.bank.core.api;

public interface TransferAction {

	void transfer(Double amount,String sourceAccountId,String targetAccountId);
}
