package org.mycontrib.hex.bank.domain.api;

public interface TransferAction {

	void transfer(Double amount,String sourceAccountId,String targetAccountId);
}
