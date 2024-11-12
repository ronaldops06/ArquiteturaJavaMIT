package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import br.edu.ifnet.ronaldo.model.domain.Endereco;
import br.edu.ifnet.ronaldo.model.service.EnderecoService;

@Component
public class EnderecoLoad {
	
	private EnderecoService enderecoService;
	
	public EnderecoLoad(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	protected void loadEnderecos() throws Exception {
		FileReader file = new FileReader("files/enderecos.txt");
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
			Endereco endereco = new Endereco();
			endereco.setCep(campos[0]);
			endereco.setLogradouro(campos[1]);
			endereco.setNumero(Integer.parseInt(campos[2]));
			endereco.setBairro(campos[3]);
			endereco.setComplemento(campos[4]);
			endereco.setCidade(campos[5]);
			endereco.setUf(campos[6]);
			endereco.setPais(campos[7]);
			
			enderecoService.incluir(endereco);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
