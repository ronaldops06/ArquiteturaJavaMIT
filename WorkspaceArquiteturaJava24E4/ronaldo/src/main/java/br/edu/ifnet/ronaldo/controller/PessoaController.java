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
import br.edu.ifnet.ronaldo.model.domain.Pessoa;
import br.edu.ifnet.ronaldo.model.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;
	
	@Operation(summary = "Recupera todas as pessoas existentes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping
	public ResponseEntity<Collection<Pessoa>> obterLista(){
		
		return ResponseEntity.ok(pessoaService.obterLista());
	}
	
	@Operation(summary = "Inclui uma nova pessoa.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@Valid @RequestBody Pessoa pessoa) {
		
		pessoaService.incluir(pessoa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera uma pessoa existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<Pessoa> alterar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaAtualizado = pessoaService.alterar(pessoa);
		
		return ResponseEntity.ok(pessoaAtualizado);
	}
	
	@Operation(summary = "Exclui uma pessoa através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(pessoaService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca uma pessoa através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> obterPorId(@PathVariable Integer id) {
		Pessoa pessoa = pessoaService.obterPorId(id);
		
		if(pessoa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
}
