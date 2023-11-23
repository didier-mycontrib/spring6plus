package org.mycontrib.hex.bank.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.mycontrib.hex.bank.core.domain.entity.Devise;
import org.mycontrib.hex.bank.core.spi.DeviseLoader;
import org.mycontrib.hex.bank.persistence.dao.DeviseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviseLoaderAdapter implements DeviseLoader {
	
	@Autowired
	private DeviseJpaRepository deviseRepository;
	


	@Override
	public Optional<Devise> loadById(String id,String... wishedDetails) {
		return Optional.of(
				  EntityConverter.INSTANCE.map(
					  deviseRepository.findById(id).orElse(null),
                      Devise.class)
				  );
	}

	@Override
	public boolean existsWithThisId(String id) {
		return deviseRepository.existsById(id);
	}

	@Override
	public List<Devise> loadAll() {
		return EntityConverter.INSTANCE.map(deviseRepository.findAll(),
				                                     Devise.class);
	}

	
}
