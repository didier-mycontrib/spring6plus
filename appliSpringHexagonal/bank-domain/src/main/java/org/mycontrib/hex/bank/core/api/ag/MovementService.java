package org.mycontrib.hex.bank.core.api.ag;

import org.mycontrib.hex.bank.core.api.DepositAction;
import org.mycontrib.hex.bank.core.api.TransferAction;
import org.mycontrib.hex.bank.core.api.WithdrawAction;

public interface MovementService extends TransferAction, DepositAction, WithdrawAction{

}
