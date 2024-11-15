package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;
import br.edu.ifnet.ronaldo.model.domain.SalaFesta;

@Repository
public interface SalaFestaRepository extends CrudRepository<SalaFesta, Integer>, SalaRepository<SalaFesta>{
	SalaFesta findByNomeAndAndar_Id(String nome, long andarId);
	
	Iterable<SalaFesta> findAll(Sort by);
}
