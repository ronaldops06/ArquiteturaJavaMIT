package br.edu.ifnet.ronaldo.model.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.domain.Mesa;
import br.edu.ifnet.ronaldo.model.domain.Pessoa;
import br.edu.ifnet.ronaldo.model.domain.Reserva;
import br.edu.ifnet.ronaldo.model.domain.SalaAuditorio;
import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;
import br.edu.ifnet.ronaldo.model.domain.SalaFesta;
import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;
import br.edu.ifnet.ronaldo.model.domain.SalaReuniao;
import br.edu.ifnet.ronaldo.model.domain.TipoSala;
import br.edu.ifnet.ronaldo.model.domain.Vaga;
import br.edu.ifnet.ronaldo.model.service.AndarService;
import br.edu.ifnet.ronaldo.model.service.EscritorioService;
import br.edu.ifnet.ronaldo.model.service.MesaService;
import br.edu.ifnet.ronaldo.model.service.PessoaService;
import br.edu.ifnet.ronaldo.model.service.ReservaService;
import br.edu.ifnet.ronaldo.model.service.SalaAuditorioService;
import br.edu.ifnet.ronaldo.model.service.SalaEscritorioService;
import br.edu.ifnet.ronaldo.model.service.SalaFestaService;
import br.edu.ifnet.ronaldo.model.service.SalaGaragemService;
import br.edu.ifnet.ronaldo.model.service.SalaReuniaoService;
import br.edu.ifnet.ronaldo.model.service.VagaService;

public class ReservaLoad {
	
	private EscritorioService escritorioService;
	private AndarService andarService;
	private ReservaService reservaService;
	private PessoaService pessoaService;
	private SalaEscritorioService salaEscritorioService;
	private SalaGaragemService salaGaragemService;
	private SalaReuniaoService salaReuniaoService;
	private SalaAuditorioService salaAuditorioService;
	private SalaFestaService salaFestaService;
	private MesaService mesaService;
	private VagaService vagaService;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss", Locale.ENGLISH);
	
	public ReservaLoad(EscritorioService escritorioService,
					   AndarService andarService,
			           ReservaService reservaService,
			           PessoaService pessoaService,
			           SalaEscritorioService salaEscritorioService,
			           SalaGaragemService salaGaragemService,
			           SalaReuniaoService salaReuniaoService,
			           SalaAuditorioService salaAuditorioService,
			           SalaFestaService salaFestaService,
			           MesaService mesaService,
			           VagaService vagaService) {
		this.escritorioService = escritorioService;
		this.andarService = andarService;
		this.reservaService = reservaService;
		this.pessoaService = pessoaService;
		this.salaEscritorioService = salaEscritorioService;
		this.salaGaragemService = salaGaragemService;
		this.salaReuniaoService = salaReuniaoService;
		this.salaAuditorioService = salaAuditorioService;
		this.salaFestaService = salaFestaService;
		this.mesaService = mesaService;
		this.vagaService = vagaService;
	}
	
	public void LoadReservasSalas() throws Exception {
		FileReader file = new FileReader("files/reservas_salas.txt");
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
			Reserva reserva = new Reserva();
			reserva.setDataInicio(formatter.parse(campos[1]));
			reserva.setDataFim(formatter.parse(campos[2]));
			
			Pessoa pessoa = pessoaService.obterPorCpf(campos[3]);
			reserva.setPessoa(pessoa);
			
			Escritorio escritorio = escritorioService.findByEndereco(campos[4], Integer.parseInt(campos[5]));
			Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[6]), escritorio.getId());
			
			TipoSala tipoSala = TipoSala.values()[Integer.parseInt(campos[0])];
			
			switch(tipoSala) {
				case Auditorio:
					SalaAuditorio salaAuditorio = salaAuditorioService.findByNomeAndAndar(campos[7], andar.getId());
					reserva.setSala(salaAuditorio);
					break;
				case Festa:
					SalaFesta salaFesta = salaFestaService.findByNomeAndAndar(campos[7], andar.getId());
					reserva.setSala(salaFesta);
					break;
				case Reuniao:
					SalaReuniao salaReuniao = salaReuniaoService.findByNomeAndAndar(campos[7], andar.getId());
					reserva.setSala(salaReuniao);
					break;
				default:
					break;
			}
									
			reservaService.incluir(reserva);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
	
	public void LoadReservasMesas() throws Exception {
		FileReader file = new FileReader("files/reservas_mesas.txt");
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
			Reserva reserva = new Reserva();
			reserva.setDataInicio(formatter.parse(campos[0]));
			reserva.setDataFim(formatter.parse(campos[1]));
			
			Pessoa pessoa = pessoaService.obterPorCpf(campos[2]);
			reserva.setPessoa(pessoa);
			
			Escritorio escritorio = escritorioService.findByEndereco(campos[3], Integer.parseInt(campos[4]));
			Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[5]), escritorio.getId());
			SalaEscritorio salaEscritorio = salaEscritorioService.findByNomeAndAndar(campos[6], andar.getId());
			Mesa mesa = mesaService.findByLocalAndSala(campos[7], salaEscritorio.getId());
			reserva.setMesa(mesa);
			
			reservaService.incluir(reserva);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
	
	public void LoadReservasVagas() throws Exception {
		FileReader file = new FileReader("files/reservas_vagas.txt");
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
			Reserva reserva = new Reserva();
			reserva.setDataInicio(formatter.parse(campos[0]));
			reserva.setDataFim(formatter.parse(campos[1]));
			
			Pessoa pessoa = pessoaService.obterPorCpf(campos[2]);
			reserva.setPessoa(pessoa);
			
			Escritorio escritorio = escritorioService.findByEndereco(campos[3], Integer.parseInt(campos[4]));
			Andar andar = andarService.findByNumeroAndEscritorio(Integer.parseInt(campos[5]), escritorio.getId());
			SalaGaragem salaGaragem = salaGaragemService.findByNomeAndAndar(campos[6], andar.getId());
			Vaga vaga = vagaService.findByCodigoAndSala(campos[7], salaGaragem.getId());
			reserva.setVaga(vaga);
			
			reservaService.incluir(reserva);
			
			linha = leitura.readLine();
			numeroLinha++;
		}

		leitura.close();
	}
}
