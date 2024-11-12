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
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.service.EscritorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/escritorio")
public class EscritorioController {
	
	@Autowired
	private EscritorioService escritorioService;
	
	@Operation(summary = "Recupera todos os escritórios existentes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping
	public ResponseEntity<Collection<Escritorio>> obterLista(){
		
		return ResponseEntity.ok(escritorioService.obterLista());
	}
	
	@Operation(summary = "Inclui um novo escritório.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@Valid @RequestBody Escritorio escritorio) {
		
		escritorioService.incluir(escritorio);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera um escritório existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<Escritorio> alterar(@Valid @RequestBody Escritorio escritorio) {
		Escritorio escritorioAtualizado = escritorioService.alterar(escritorio);
		
		return ResponseEntity.ok(escritorioAtualizado);
	}
	
	@Operation(summary = "Exclui um escritório através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(escritorioService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca um escritóio através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Escritorio> obterPorId(@PathVariable Integer id) {
		Escritorio escritorio = escritorioService.obterPorId(id);
		
		if(escritorio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(escritorio);
	}
}
