package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.service.AndarService;
import br.edu.ifnet.ronaldo.model.service.EscritorioService;

@Component
public class AndarLoad{

	private EscritorioService escritorioService;
	private AndarService andarService;
	
	public AndarLoad(EscritorioService escritorioService, AndarService andarService) {
		this.escritorioService = escritorioService;
		this.andarService = andarService;
	}
	
	protected void loadAndar() throws Exception {
		FileReader file = new FileReader("files/andares.txt");
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
			Andar andar = new Andar();
			andar.setNumero(Integer.parseInt(campos[0]));
			
			Escritorio escritorio = escritorioService.findByEndereco(Long.parseLong(campos[1]), Integer.parseInt(campos[2]));
			andar.setEscritorio(escritorio);
			
			andarService.incluir(andar);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
