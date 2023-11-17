package org.mycontrib.hex.bank.domain.api;

public interface DepositAction {
	void credit(String accountId,Double amount);
}
