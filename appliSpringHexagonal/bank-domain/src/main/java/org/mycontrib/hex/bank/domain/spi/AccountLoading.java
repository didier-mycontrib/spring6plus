package org.mycontrib.hex.bank.domain.spi;

import org.mycontrib.hex.bank.domain.entity.Account;
import org.mycontrib.hex.generic.domain.spi.Loading;

//Query (readOnly) part of persistence
public interface AccountLoading extends Loading<Account,String>{
}
