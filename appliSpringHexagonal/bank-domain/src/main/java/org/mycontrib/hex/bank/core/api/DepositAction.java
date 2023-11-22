package org.mycontrib.hex.bank.core.api;

public interface DepositAction {
	void credit(String accountId,Double amount);
}
