package org.mycontrib.hex.bank.core.impl;

import org.mycontrib.hex.bank.core.api.ag.DeviseService;
import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.bank.core.spi.DeviseLoader;
import org.mycontrib.hex.bank.core.spi.DeviseSaver;
import org.mycontrib.hex.generic.core.impl.GenericQueryingAndLifeCycleAbstract;

//@Service and @Transactional will be done outside this agnostic code
public class DeviseServiceImpl 
 extends GenericQueryingAndLifeCycleAbstract<Devise,String>
   implements DeviseService{
	
	//SPI to inject:
	private DeviseLoader deviseLoader;
	private DeviseSaver deviseSaver;
	

	public DeviseServiceImpl(DeviseLoader deviseLoader , DeviseSaver deviseSaver) {
		super(deviseLoader,deviseSaver);
		// constructor for SPI dependencies injections 
		this.deviseLoader=deviseLoader;
		this.deviseSaver=deviseSaver;
	}	

}
