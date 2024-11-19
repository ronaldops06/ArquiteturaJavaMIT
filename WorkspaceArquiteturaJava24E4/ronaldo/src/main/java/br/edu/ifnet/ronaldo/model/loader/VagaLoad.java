package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;
import br.edu.ifnet.ronaldo.model.domain.TipoVaga;
import br.edu.ifnet.ronaldo.model.domain.Vaga;
import br.edu.ifnet.ronaldo.model.service.AndarService;
import br.edu.ifnet.ronaldo.model.service.EscritorioService;
import br.edu.ifnet.ronaldo.model.service.SalaGaragemService;
import br.edu.ifnet.ronaldo.model.service.VagaService;

public class VagaLoad {
	
	private EscritorioService escritorioService;
	private AndarService andarService;
	private SalaGaragemService salaGaragemService;
	private VagaService vagaService;
	
	public VagaLoad(EscritorioService escritorioService,
	        		AndarService andarService,
	        		SalaGaragemService salaGaragemService,
	        		VagaService vagaService) {
		this.escritorioService = escritorioService;
		this.andarService = andarService;
		this.salaGaragemService = salaGaragemService;
		this.vagaService = vagaService;
	}
	
	protected void loadVagas() throws Exception {
		FileReader file = new FileReader("files/vagas.txt");
		BufferedReader leitura = new BufferedReader(file);

		String linha = leitura.readLine();
		int numeroLinha = 1;
		
		while(linha != null) {
			if (numeroLinha == 1) {
				linha = leitura.readLine();
				numeroLinha++;
				continue;
			}
			
			String[] campos = linha.split(";");
			Vaga vaga = new Vaga();
			vaga.setCodigo(campos[0]);
			
			TipoVaga tipoVaga = TipoVaga.values()[Integer.parseInt(campos[1])];
			vaga.setTipo(tipoVaga);
			
			vaga.setPossuiCarregador(Boolean.parseBoolean(campos[2]));
			vaga.setAtivo(Boolean.parseBoolean(campos[3]));
			
			Escritorio escritorio = escritorioService.findByEndereco(campos[4], Integer.parseInt(campos[5]));
			Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[6]), escritorio.getId());
			SalaGaragem salaGaragem = salaGaragemService.findByNomeAndAndar(campos[7], andar.getId());
			vaga.setSalaGaragem(salaGaragem);
			
			vagaService.incluir(vaga);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
