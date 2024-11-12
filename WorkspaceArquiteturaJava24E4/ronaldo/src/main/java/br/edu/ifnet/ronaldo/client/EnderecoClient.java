package br.edu.ifnet.ronaldo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifnet.ronaldo.model.domain.Endereco;

@FeignClient(url = "https://viacep.com.br/ws", name = "enderecoClient")
@Component
public interface EnderecoClient {
	
	@GetMapping(value = "/{cep}/json/")
	Endereco findByCep(@PathVariable String cep);
}
