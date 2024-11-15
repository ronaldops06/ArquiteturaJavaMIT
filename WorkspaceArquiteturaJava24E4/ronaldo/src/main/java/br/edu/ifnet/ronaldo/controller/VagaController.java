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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.model.domain.Vaga;
import br.edu.ifnet.ronaldo.model.service.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vaga")
public class VagaController {
	@Autowired
	private VagaService vagaService;
	
	@Operation(summary = "Recupera todos as vagas existentes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping
	public ResponseEntity<Collection<Vaga>> obterLista(){
		
		return ResponseEntity.ok(vagaService.obterLista());
	}
	
	@Operation(summary = "Inclui uma nova vaga.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@Valid @RequestBody Vaga vaga) {
		
		vagaService.incluir(vaga);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera uma vaga existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<Vaga> alterar(@Valid @RequestBody Vaga vaga) {
		Vaga vagaAtualizado = vagaService.alterar(vaga);
		
		return ResponseEntity.ok(vagaAtualizado);
	}
	
	@Operation(summary = "Exclui uma vaga através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(vagaService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca uma vaga através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vaga> obterPorId(@PathVariable Integer id) {
		Vaga vaga = vagaService.obterPorId(id);
		
		if(vaga == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(vaga);
	}
}
