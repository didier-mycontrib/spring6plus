package org.mycontrib.hex.bank.domain.api.ag;

import org.mycontrib.hex.bank.domain.api.AccountLifeCycle;
import org.mycontrib.hex.bank.domain.api.AccountQuerying;

public interface AccountService extends MovementService , AccountQuerying , AccountLifeCycle  {

}
