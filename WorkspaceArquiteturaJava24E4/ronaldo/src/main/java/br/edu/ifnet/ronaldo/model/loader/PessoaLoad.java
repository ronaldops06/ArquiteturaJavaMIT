package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import br.edu.ifnet.ronaldo.model.domain.Pessoa;
import br.edu.ifnet.ronaldo.model.service.PessoaService;

@Component
public class PessoaLoad {
	
	private PessoaService pessoaService;
	
	public PessoaLoad(PessoaService pessoaSevice) {
		this.pessoaService = pessoaSevice;
	}
	
	protected void loadPessoas() throws Exception {
		FileReader file = new FileReader("files/pessoas.txt");
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
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(campos[0]);
			pessoa.setCpf(campos[1]);
						
			pessoaService.incluir(pessoa);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
