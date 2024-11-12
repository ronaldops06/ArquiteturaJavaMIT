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
import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.service.AndarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/andar")
public class AndarController {
	
	@Autowired
	private AndarService andarService;
	
	@Operation(summary = "Recupera todos os andares existentes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping
	public ResponseEntity<Collection<Andar>> obterLista(){
		
		return ResponseEntity.ok(andarService.obterLista());
	}
	
	@Operation(summary = "Inclui um novo andar.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@Valid @RequestBody Andar andar) {
		
		andarService.incluir(andar);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera um andar existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<Andar> alterar(@Valid @RequestBody Andar andar) {
		Andar andarAtualizado = andarService.alterar(andar);
		
		return ResponseEntity.ok(andarAtualizado);
	}
	
	@Operation(summary = "Exclui um andar através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(andarService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca um andar através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Andar> obterPorId(@PathVariable Integer id) {
		Andar andar = andarService.obterPorId(id);
		
		if(andar == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(andar);
	}
}
