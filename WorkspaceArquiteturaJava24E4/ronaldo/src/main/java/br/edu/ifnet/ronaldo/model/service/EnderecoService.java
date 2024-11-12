package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.client.EnderecoClient;
import br.edu.ifnet.ronaldo.model.domain.Endereco;
import br.edu.ifnet.ronaldo.model.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoClient enderecoClient;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco incluir(Endereco endereco) {
		
		if (endereco.getLogradouro() == null) {
			Endereco enderecoCompleto = enderecoClient.findByCep(endereco.getCep());
			enderecoCompleto.setComplemento(endereco.getComplemento());
			enderecoCompleto.setNumero(endereco.getNumero());
			enderecoCompleto.setPais(endereco.getPais());
			enderecoCompleto.setCidade(enderecoCompleto.getCidade());
			endereco = enderecoCompleto;
		}
		
		return enderecoRepository.save(endereco);
	}
	
	public void excluir(Integer id) {
		enderecoRepository.deleteById(id);
	}
	
	public Endereco findByCepAndNumero(String cep, int numero) {
		
		return enderecoRepository.findByCepAndNumero(cep, numero);
	}
	
	/*public Endereco findByCep(String cep) {
		return enderecoClient.findByCep(cep);
	}*/
}
