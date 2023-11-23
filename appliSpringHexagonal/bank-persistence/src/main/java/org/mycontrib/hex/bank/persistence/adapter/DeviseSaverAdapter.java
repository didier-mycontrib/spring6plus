package org.mycontrib.hex.bank.persistence.adapter;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.bank.core.spi.DeviseSaver;
import org.mycontrib.hex.bank.persistence.dao.DeviseJpaRepository;
import org.mycontrib.hex.bank.persistence.entity.DeviseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviseSaverAdapter implements DeviseSaver {
	
	@Autowired
	private DeviseJpaRepository deviseRepository;
	
	
	@Override
	public Devise saveNew(Devise devise) {
		DeviseEntity deviseEntity = EntityConverter.INSTANCE.map(devise,DeviseEntity.class);
		deviseEntity = deviseRepository.save(deviseEntity);
		devise.setCode(String.valueOf(deviseEntity.getCode()));
		return devise;
	}
	
	@Override
	public void updateExisting(Devise devise) {
		DeviseEntity deviseEntity = EntityConverter.INSTANCE.map(devise,DeviseEntity.class);
		deviseRepository.save(deviseEntity);
	}

	@Override
	public void deleteFromId(String id) {
		deviseRepository.deleteById(id);
	}

	@Override
	public void remove(Devise entity) {
		deviseRepository.delete(EntityConverter.INSTANCE.map(entity,DeviseEntity.class));
	}

}
