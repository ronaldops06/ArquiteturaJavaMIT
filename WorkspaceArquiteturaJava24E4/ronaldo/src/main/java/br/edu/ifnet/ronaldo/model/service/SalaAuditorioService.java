package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.model.domain.SalaAuditorio;
import br.edu.ifnet.ronaldo.model.repository.SalaAuditorioRepository;

@Service
@Component
public class SalaAuditorioService extends SalaService<SalaAuditorio> {

	@Autowired
	public SalaAuditorioService(SalaAuditorioRepository salaRepository) {
		super(salaRepository);
	}

}
