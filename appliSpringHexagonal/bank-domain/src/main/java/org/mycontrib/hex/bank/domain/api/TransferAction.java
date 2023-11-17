package org.mycontrib.hex.bank.domain.api;

public interface TransferAction {
	
	
	void transfer(String sourceAccountId,String targetAccountId,Double amount);
}
