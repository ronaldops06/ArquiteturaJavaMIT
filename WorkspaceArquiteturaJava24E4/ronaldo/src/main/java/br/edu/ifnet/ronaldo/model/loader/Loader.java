package br.edu.ifnet.ronaldo.model.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.ifnet.ronaldo.model.service.AndarService;
import br.edu.ifnet.ronaldo.model.service.EnderecoService;
import br.edu.ifnet.ronaldo.model.service.EscritorioService;
import br.edu.ifnet.ronaldo.model.service.SalaAuditorioService;
import br.edu.ifnet.ronaldo.model.service.SalaEscritorioService;
import br.edu.ifnet.ronaldo.model.service.SalaFestaService;
import br.edu.ifnet.ronaldo.model.service.SalaGaragemService;
import br.edu.ifnet.ronaldo.model.service.SalaReuniaoService;

@Component
public class Loader implements ApplicationRunner{
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private EscritorioService escritorioService;
	@Autowired
	private AndarService andarService;
	@Autowired
	private SalaAuditorioService salaAuditorioService;
	@Autowired
	private SalaEscritorioService salaEscritorioService;
	@Autowired
	private SalaFestaService salaFestaService;
	@Autowired
	private SalaGaragemService salaGaragemService;
	@Autowired
	private SalaReuniaoService salaReuniaoService;
	
	public void run(ApplicationArguments args) throws Exception {
		
		EnderecoLoad enderecoLoad = new EnderecoLoad(enderecoService);
		EscritorioLoad escritorioLoad = new EscritorioLoad(enderecoService, escritorioService);
		AndarLoad andarLoad = new AndarLoad(escritorioService, andarService);
		SalaLoad salaLoad = new SalaLoad(andarService, salaAuditorioService, salaEscritorioService, salaFestaService, salaGaragemService, salaReuniaoService);
		
		enderecoLoad.loadEnderecos();
		escritorioLoad.loadEscritorios();
		andarLoad.loadAndar();
		salaLoad.loadSalas();
	}
}
