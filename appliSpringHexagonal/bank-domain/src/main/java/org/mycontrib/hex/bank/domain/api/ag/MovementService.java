package org.mycontrib.hex.bank.domain.api.ag;

import org.mycontrib.hex.bank.domain.api.DepositAction;
import org.mycontrib.hex.bank.domain.api.TransferAction;
import org.mycontrib.hex.bank.domain.api.WithdrawAction;

public interface MovementService extends TransferAction, DepositAction, WithdrawAction{

}
