package org.mycontrib.hex.bank.core.api;

public interface WithdrawAction {
	void debit(String accountId,Double amount);
}
