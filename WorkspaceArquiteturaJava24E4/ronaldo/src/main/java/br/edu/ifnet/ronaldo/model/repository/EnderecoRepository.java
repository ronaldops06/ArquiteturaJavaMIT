package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifnet.ronaldo.model.domain.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
	
	Endereco findByCepAndNumero(String cep, int numero);
}