package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;
import br.edu.ifnet.ronaldo.model.repository.SalaEscritorioRepository;
import br.edu.ifnet.ronaldo.model.repository.SalaRepository;

@Service
public class SalaEscritorioService extends SalaService<SalaEscritorio>{

	@Autowired
	public SalaEscritorioService(SalaEscritorioRepository salaRepository) {
		super(salaRepository);
	}	
}
