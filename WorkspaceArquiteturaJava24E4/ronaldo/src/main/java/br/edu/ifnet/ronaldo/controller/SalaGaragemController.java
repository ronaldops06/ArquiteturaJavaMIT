package br.edu.ifnet.ronaldo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;
import br.edu.ifnet.ronaldo.model.service.SalaGaragemService;

@Controller
@RequestMapping("/sala-garagem")
public class SalaGaragemController extends SalaController<SalaGaragem> {
	
	@Autowired
	public SalaGaragemController(SalaGaragemService salaService) {
		super(salaService);
	}
}
