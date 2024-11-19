package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.Vaga;

@Repository
public interface VagaRepository extends CrudRepository<Vaga, Integer> {
	Vaga findByCodigoAndSalaGaragem_Id(String codigo, long salaGaragemId);
	
	Iterable<Vaga> findAll(Sort by);
}
