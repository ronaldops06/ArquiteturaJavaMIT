package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import br.edu.ifnet.ronaldo.model.domain.Mesa;
import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;
import br.edu.ifnet.ronaldo.model.service.MesaService;
import br.edu.ifnet.ronaldo.model.service.SalaEscritorioService;

public class MesaLoad {
	
	private SalaEscritorioService salaEscritorioService;
	private MesaService mesaService;
	
	public MesaLoad(SalaEscritorioService salaEscritorioService, MesaService mesaService) {
		this.salaEscritorioService = salaEscritorioService;
		this.mesaService = mesaService;
	}
	
	protected void loadMesas() throws Exception {
		FileReader file = new FileReader("files/mesas.txt");
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
			Mesa mesa = new Mesa();
			mesa.setLocal(campos[0]);
			mesa.setAtivo(Boolean.parseBoolean(campos[1]));
					
			SalaEscritorio sala = salaEscritorioService.findByNomeAndAndarAndEscritorio(campos[5], Integer.parseInt(campos[4]), campos[2], Integer.parseInt(campos[3]));
			mesa.setSalaEscritorio(sala);
			
			mesaService.incluir(mesa);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
