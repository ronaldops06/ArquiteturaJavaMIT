package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.domain.SalaAuditorio;
import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;
import br.edu.ifnet.ronaldo.model.domain.SalaFesta;
import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;
import br.edu.ifnet.ronaldo.model.domain.SalaReuniao;
import br.edu.ifnet.ronaldo.model.domain.TipoSala;
import br.edu.ifnet.ronaldo.model.service.AndarService;
import br.edu.ifnet.ronaldo.model.service.SalaAuditorioService;
import br.edu.ifnet.ronaldo.model.service.SalaEscritorioService;
import br.edu.ifnet.ronaldo.model.service.SalaFestaService;
import br.edu.ifnet.ronaldo.model.service.SalaGaragemService;
import br.edu.ifnet.ronaldo.model.service.SalaReuniaoService;

@Component
public class SalaLoad {
	private AndarService andarService;
	private SalaAuditorioService salaAuditorioService;
	private SalaEscritorioService salaEscritorioService;
	private SalaFestaService salaFestaService;
	private SalaGaragemService salaGaragemService;
	private SalaReuniaoService salaReuniaoService;
	private SimpleDateFormat formatter;
	
	public SalaLoad(AndarService andarService, 
			        SalaAuditorioService salaAuditorioService, 
			        SalaEscritorioService salaEscritorioService,
			        SalaFestaService salaFestaService,
			        SalaGaragemService salaGaragemService,
			        SalaReuniaoService salaReuniaoService) {
		this.andarService = andarService;
		this.salaAuditorioService = salaAuditorioService;
		this.salaEscritorioService = salaEscritorioService;
		this.salaFestaService = salaFestaService;
		this.salaGaragemService = salaGaragemService;
		this.salaReuniaoService = salaReuniaoService;
	}
	
	protected void loadSalas() throws Exception {
		FileReader file = new FileReader("files/salas.txt");
		BufferedReader leitura = new BufferedReader(file);

		formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss", Locale.ENGLISH);
		
		String linha = leitura.readLine();
		int numeroLinha = 1;
		
		while(linha != null) {
			if (numeroLinha == 1) {
				linha = leitura.readLine();
				numeroLinha++;
				continue;
			}
			
			String[] campos = linha.split(";");
						
			TipoSala tipoSala = TipoSala.values()[Integer.parseInt(campos[3])];
						
			switch(tipoSala) {
				case Auditorio:
					loadSalaAuditorio(campos, tipoSala);
					break;
				case Escritorio:
					loadSalaEscritorio(campos, tipoSala);
					break;
				case Festa:
					loadSalaFesta(campos, tipoSala);
					break;
				case Garagem:
					loadSalaGaragem(campos, tipoSala);
					break;
				case Reuniao:
					loadSalaReuniao(campos, tipoSala);
					break;
			}
			
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
	
	private void loadSalaAuditorio(String[] campos, TipoSala tipoSala) throws Exception {
		SalaAuditorio sala = new SalaAuditorio();
		sala.setNome(campos[0]);
		sala.setCapacidade(Integer.parseInt(campos[1]));
		sala.setAtivo(Boolean.parseBoolean(campos[2]));
		sala.setTipo(tipoSala);
		if (campos[4] != null && !campos[4].isEmpty()) {
			sala.setTempoMaximoReserva(Integer.parseInt(campos[4]));
		}
		if (campos[5] != null && !campos[5].isBlank()) {
			sala.setHoraMinimaReserva(formatter.parse(campos[5]));
		}
		if (campos[6] != null && !campos[6].isBlank()) {
			sala.setHoraMaximaReserva(formatter.parse(campos[6]));
		}
		
		Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[9]), campos[7], Integer.parseInt(campos[8]));
		sala.setAndar(andar);
		
		salaAuditorioService.incluir(sala);
	}
	
	private void loadSalaEscritorio(String[] campos, TipoSala tipoSala) throws Exception {
		SalaEscritorio sala = new SalaEscritorio();
		sala.setNome(campos[0]);
		sala.setCapacidade(Integer.parseInt(campos[1]));
		sala.setAtivo(Boolean.parseBoolean(campos[2]));
		sala.setTipo(tipoSala);
		
		Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[9]), campos[7], Integer.parseInt(campos[8]));
		sala.setAndar(andar);
		
		salaEscritorioService.incluir(sala);
	}
	
	private void loadSalaFesta(String[] campos, TipoSala tipoSala) throws Exception {
		SalaFesta sala = new SalaFesta();
		sala.setNome(campos[0]);
		sala.setCapacidade(Integer.parseInt(campos[1]));
		sala.setAtivo(Boolean.parseBoolean(campos[2]));
		sala.setTipo(tipoSala);
		if (campos[4] != null && !campos[4].isEmpty()) {
			sala.setTempoMaximoReserva(Integer.parseInt(campos[4]));
		}
		if (campos[5] != null && !campos[5].isBlank()) {
			sala.setHoraMinimaReserva(formatter.parse(campos[5]));
		}
		if (campos[6] != null && !campos[6].isBlank()) {
			sala.setHoraMaximaReserva(formatter.parse(campos[6]));
		}
		
		Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[9]), campos[7], Integer.parseInt(campos[8]));
		sala.setAndar(andar);
		
		salaFestaService.incluir(sala);
	}
	
	private void loadSalaGaragem(String[] campos, TipoSala tipoSala) throws Exception {
		SalaGaragem sala = new SalaGaragem();
		sala.setNome(campos[0]);
		sala.setCapacidade(Integer.parseInt(campos[1]));
		sala.setAtivo(Boolean.parseBoolean(campos[2]));
		sala.setTipo(tipoSala);		
		
		Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[9]), campos[7], Integer.parseInt(campos[8]));
		sala.setAndar(andar);
		
		salaGaragemService.incluir(sala);
	}
	
	private void loadSalaReuniao(String[] campos, TipoSala tipoSala) throws Exception {
		SalaReuniao sala = new SalaReuniao();
		sala.setNome(campos[0]);
		sala.setCapacidade(Integer.parseInt(campos[1]));
		sala.setAtivo(Boolean.parseBoolean(campos[2]));
		sala.setTipo(tipoSala);
		if (campos[4] != null && !campos[4].isEmpty()) {
			sala.setTempoMaximoReserva(Integer.parseInt(campos[4]));
		}
		
		Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[9]), campos[7], Integer.parseInt(campos[8]));
		sala.setAndar(andar);
		
		salaReuniaoService.incluir(sala);
	}
}
