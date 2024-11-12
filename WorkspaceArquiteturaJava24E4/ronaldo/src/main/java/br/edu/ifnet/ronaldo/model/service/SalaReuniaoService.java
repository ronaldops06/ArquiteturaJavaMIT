package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.model.domain.SalaReuniao;
import br.edu.ifnet.ronaldo.model.repository.SalaRepository;
import br.edu.ifnet.ronaldo.model.repository.SalaReuniaoRepository;

@Service
public class SalaReuniaoService extends SalaService<SalaReuniao>{

	@Autowired
	public SalaReuniaoService(SalaReuniaoRepository salaRepository) {
		super(salaRepository);
	}

}
