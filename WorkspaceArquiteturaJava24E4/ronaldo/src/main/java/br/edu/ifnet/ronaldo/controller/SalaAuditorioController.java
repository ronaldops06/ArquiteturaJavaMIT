package br.edu.ifnet.ronaldo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifnet.ronaldo.model.domain.SalaAuditorio;
import br.edu.ifnet.ronaldo.model.service.SalaAuditorioService;

@Controller
@RequestMapping("/sala-auditorio")
public class SalaAuditorioController extends SalaController<SalaAuditorio> {

	@Autowired
	public SalaAuditorioController(SalaAuditorioService salaService) {
		super(salaService); 
	}
}
