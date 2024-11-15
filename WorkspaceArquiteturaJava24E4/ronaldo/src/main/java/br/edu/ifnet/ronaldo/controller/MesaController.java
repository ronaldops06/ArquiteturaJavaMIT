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
import br.edu.ifnet.ronaldo.model.domain.Mesa;
import br.edu.ifnet.ronaldo.model.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mesa")
public class MesaController {
	
	@Autowired
	private MesaService mesaService;
	
	@Operation(summary = "Recupera todos as Mesas existentes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema")
		})
	@GetMapping
	public ResponseEntity<Collection<Mesa>> obterLista(){
		
		return ResponseEntity.ok(mesaService.obterLista());
	}
	
	@Operation(summary = "Inclui uma nova Mesa.")
	@PostMapping(value = "/incluir")
	public ResponseEntity<String> incluir(@Valid @RequestBody Mesa Mesa) {
		
		mesaService.incluir(Mesa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_INCLUSAO_SUCESSO);
	}
	
	@Operation(summary = "Altera uma Mesa existente.")
	@PutExchange(value = "/alterar")
	public ResponseEntity<Mesa> alterar(@Valid @RequestBody Mesa Mesa) {
		Mesa MesaAtualizado = mesaService.alterar(Mesa);
		
		return ResponseEntity.ok(MesaAtualizado);
	}
	
	@Operation(summary = "Exclui uma Mesa através do ID.")
	@DeleteMapping(value = "/{id}/excluir")
	public ResponseEntity<String> excluir(@PathVariable Integer id) {
		
		if(mesaService.excluir(id)) {
			return ResponseEntity.ok(Constants.MSG_EXCLUSAO_SUCESSO);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_NOT_FOUND);
	}
	
	@Operation(summary = "Busca uma Mesa através do ID.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Mesa> obterPorId(@PathVariable Integer id) {
		Mesa Mesa = mesaService.obterPorId(id);
		
		if(Mesa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(Mesa);
	}

}
