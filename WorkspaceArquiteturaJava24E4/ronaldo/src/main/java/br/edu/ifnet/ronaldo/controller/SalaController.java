
package br.edu.ifnet.ronaldo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.PutExchange;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.model.domain.Sala;
import br.edu.ifnet.ronaldo.model.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sala")
public class SalaController<T extends Sala> {

	private SalaService<T> salaService;

	@Autowired
	public SalaController(@Qualifier("salaAuditorioService") SalaService<T> salaService) {
		this.salaService = salaService;
	}
	
	@Operation(summary = "Recupera todos os salas existentes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping
	public ResponseEntity<Collection<T>> obterLista(){
		
		return ResponseEntity.ok(salaService.obterLista());
	}
	
	@Operation(summary = "Inclui um novo sala.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@RequestBody T sala) {
		
		salaService.incluir(sala);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera uma sala existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<T> alterar(@Valid @RequestBody T sala) {
		T salaAtualizada = salaService.alterar(sala);
		
		return ResponseEntity.ok(salaAtualizada);
	}
	
	@Operation(summary = "Exclui um sala através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(salaService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca uma sala através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<T> obterPorId(@PathVariable Integer id) {
		T sala = salaService.obterPorId(id);
		
		if(sala == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(sala);
	}
}
