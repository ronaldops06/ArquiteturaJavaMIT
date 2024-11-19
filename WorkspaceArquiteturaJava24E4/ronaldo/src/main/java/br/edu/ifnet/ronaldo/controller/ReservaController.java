package br.edu.ifnet.ronaldo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.model.domain.Reserva;
import br.edu.ifnet.ronaldo.model.domain.TipoReserva;
import br.edu.ifnet.ronaldo.model.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	private ReservaService reservaService;
	
	@Operation(summary = "Recupera as reservas existentes por pessoa.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping(value="/pessoa")
	public ResponseEntity<Collection<Reserva>> obterPorPessoa(@RequestParam Integer pessoaId){
		
		return ResponseEntity.ok(reservaService.obterPorPessoaId(pessoaId));
	}
	
	@Operation(summary = "Recupera as reservas existentes para a sala.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping(value="/sala")
	public ResponseEntity<Collection<Reserva>> obterPorSala(@RequestParam Integer salaId){
		
		return ResponseEntity.ok(reservaService.obterPorTipoReserva(TipoReserva.Sala, salaId));
	}
	
	@Operation(summary = "Recupera as reservas existentes para a mesa.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping(value="/mesa")
	public ResponseEntity<Collection<Reserva>> obterPorMesa(@RequestParam Integer mesaId){
		
		return ResponseEntity.ok(reservaService.obterPorTipoReserva(TipoReserva.Mesa, mesaId));
	}
	
	@Operation(summary = "Recupera as reservas existentes para a vaga.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping(value="/vaga")
	public ResponseEntity<Collection<Reserva>> obterPorVaga(@RequestParam Integer vagaId){
		
		return ResponseEntity.ok(reservaService.obterPorTipoReserva(TipoReserva.Vaga, vagaId));
	}
	
	@Operation(summary = "Inclui uma nova reserva.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@Valid @RequestBody Reserva reserva) {
		
		reservaService.incluir(reserva);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera uma reserva existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<Reserva> alterar(@Valid @RequestBody Reserva reserva) {
		Reserva reservaAtualizado = reservaService.alterar(reserva);
		
		return ResponseEntity.ok(reservaAtualizado);
	}
	
	@Operation(summary = "Exclui uma reserva através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(reservaService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca uma reserva através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Reserva> obterPorId(@PathVariable Integer id) {
		Reserva reserva = reservaService.obterPorId(id);
		
		if(reserva == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(reserva);
	}
}
