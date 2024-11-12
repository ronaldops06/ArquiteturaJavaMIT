package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.Escritorio;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface EscritorioRepository extends CrudRepository<Escritorio, Integer> {

	Escritorio findByEndereco_CepAndEndereco_Numero(long cep, int numero);
	
	Iterable<Escritorio> findAll(Sort by);
}
