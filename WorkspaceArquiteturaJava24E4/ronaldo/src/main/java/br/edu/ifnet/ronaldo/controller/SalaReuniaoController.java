package br.edu.ifnet.ronaldo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifnet.ronaldo.model.domain.SalaReuniao;
import br.edu.ifnet.ronaldo.model.service.SalaReuniaoService;

@Controller
@RequestMapping("/sala-reuniao")
public class SalaReuniaoController extends SalaController<SalaReuniao> {

	@Autowired
	public SalaReuniaoController(SalaReuniaoService salaService) {
		super(salaService); 
	}

}
