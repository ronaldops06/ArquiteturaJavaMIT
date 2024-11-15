package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;
import br.edu.ifnet.ronaldo.model.domain.TipoVaga;
import br.edu.ifnet.ronaldo.model.domain.Vaga;
import br.edu.ifnet.ronaldo.model.service.SalaGaragemService;
import br.edu.ifnet.ronaldo.model.service.VagaService;

public class VagaLoad {
	
	private SalaGaragemService salaGaragemService;
	private VagaService vagaService;
	
	public VagaLoad(SalaGaragemService salaGaragemService, VagaService vagaService) {
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
			
			SalaGaragem sala = salaGaragemService.findByNomeAndAndarAndEscritorio(campos[7], Integer.parseInt(campos[6]), campos[4], Integer.parseInt(campos[5]));
			vaga.setSalaGaragem(sala);
			
			vagaService.incluir(vaga);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
