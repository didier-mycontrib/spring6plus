package org.mycontrib.hex.bank.core.spi;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.generic.core.spi.Notifier;

public interface DeviseNotifier extends Notifier<Devise> {
   /*
    inherited methods:
    public void notifyNewState(T entity);
	public void notifyNewEvent(T entity,String eventHint);
    */
}
