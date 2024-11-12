package br.edu.ifnet.ronaldo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;
import br.edu.ifnet.ronaldo.model.service.SalaEscritorioService;

@Controller
@RequestMapping("/sala-escritorio")
public class SalaEscritorioController extends SalaController<SalaEscritorio> {
	
	@Autowired
	public SalaEscritorioController(SalaEscritorioService salaService) {
		super(salaService);
	}
}
