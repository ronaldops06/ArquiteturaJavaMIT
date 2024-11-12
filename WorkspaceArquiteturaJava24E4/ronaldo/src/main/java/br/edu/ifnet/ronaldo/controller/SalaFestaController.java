package br.edu.ifnet.ronaldo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifnet.ronaldo.model.domain.SalaFesta;
import br.edu.ifnet.ronaldo.model.service.SalaFestaService;

@Controller
@RequestMapping("/sala-festa")
public class SalaFestaController extends SalaController<SalaFesta> {
	
	@Autowired
	public SalaFestaController(SalaFestaService salaService) {
		super(salaService);
	}
}
