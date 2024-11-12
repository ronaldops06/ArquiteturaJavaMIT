package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import br.edu.ifnet.ronaldo.model.domain.Endereco;
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.service.EnderecoService;
import br.edu.ifnet.ronaldo.model.service.EscritorioService;

@Component
public class EscritorioLoad {
	
	private EnderecoService enderecoService;
	private EscritorioService escritorioService;
	
	public EscritorioLoad(EnderecoService enderecoService, EscritorioService escritorioService) {
		this.enderecoService = enderecoService;
		this.escritorioService = escritorioService;
	}
	
	protected void loadEscritorios() throws Exception {
		FileReader file = new FileReader("files/escritorios.txt");
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
			Escritorio escritorio = new Escritorio();
			escritorio.setNome(campos[0]);
			escritorio.setAtivo(Boolean.parseBoolean(campos[1]));
			
			Endereco endereco = enderecoService.findByCepAndNumero(campos[2], Integer.parseInt(campos[3]));
			escritorio.setEndereco(endereco);
			
			escritorioService.incluir(escritorio);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
