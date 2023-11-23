package org.mycontrib.hex.bank.messaging.adapter;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.bank.core.spi.DeviseNotifier;
import org.mycontrib.hex.bank.messaging.adapter.sender.DevisesSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviseNotifierAdapter implements DeviseNotifier {
	
	@Autowired
	private DevisesSender devisesSender;

	@Override
	public void notifyNewState(Devise entity) {
		devisesSender.sendDeviseMessage(entity);
	}

	@Override
	//eventHint may be a lifecyle event like "created" or "deleted" or else
	public void notifyNewEvent(Devise entity, String eventHint) {
		if(eventHint!=null && eventHint.equalsIgnoreCase("updated"))
			devisesSender.sendDeviseMessage(entity);
	}

}
