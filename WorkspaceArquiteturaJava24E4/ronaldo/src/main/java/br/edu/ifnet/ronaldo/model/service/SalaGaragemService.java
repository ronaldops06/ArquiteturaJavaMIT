package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;
import br.edu.ifnet.ronaldo.model.repository.SalaGaragemRepository;
import br.edu.ifnet.ronaldo.model.repository.SalaRepository;

@Service
public class SalaGaragemService extends SalaService<SalaGaragem>{

	@Autowired
	public SalaGaragemService(SalaGaragemRepository salaRepository) {
		super(salaRepository);
	}
}
