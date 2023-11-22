package org.mycontrib.hex.bank.core.api.ag;

import org.mycontrib.hex.bank.core.api.AccountLifeCycle;
import org.mycontrib.hex.bank.core.api.AccountQuerying;

public interface AccountService extends MovementService , AccountQuerying , AccountLifeCycle  {

}
