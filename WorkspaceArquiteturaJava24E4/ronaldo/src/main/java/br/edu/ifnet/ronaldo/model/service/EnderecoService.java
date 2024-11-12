package br.edu.ifnet.ronaldo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.model.domain.Endereco;
import br.edu.ifnet.ronaldo.model.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco incluir(Endereco endereco) {
		
		return enderecoRepository.save(endereco);
	}
	
	public void excluir(Integer id) {
		enderecoRepository.deleteById(id);
	}
	
	public Endereco findByCepAndNumero(long cep, int numero) {
		
		return enderecoRepository.findByCepAndNumero(cep, numero);
	}
}
