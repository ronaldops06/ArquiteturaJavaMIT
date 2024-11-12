package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.model.domain.SalaFesta;
import br.edu.ifnet.ronaldo.model.repository.SalaFestaRepository;
import br.edu.ifnet.ronaldo.model.repository.SalaRepository;

@Service
public class SalaFestaService extends SalaService<SalaFesta> {

	@Autowired
	public SalaFestaService(SalaFestaRepository salaRepository) {
		super(salaRepository);
	}
}
