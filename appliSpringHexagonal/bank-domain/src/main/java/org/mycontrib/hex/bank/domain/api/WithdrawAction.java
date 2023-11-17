package org.mycontrib.hex.bank.domain.api;

public interface WithdrawAction {
	void debit(String accountId,Double amount);
}
