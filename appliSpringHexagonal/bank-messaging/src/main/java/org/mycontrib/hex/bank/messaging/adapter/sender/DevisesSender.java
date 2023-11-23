package org.mycontrib.hex.bank.messaging.adapter.sender;

import org.mycontrib.hex.bank.core.domain.entity.Devise;

public interface DevisesSender {
	public void sendMessage(String message);
	public void sendDeviseMessage(Devise devise) ;
}
