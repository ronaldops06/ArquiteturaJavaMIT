package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.Mesa;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Integer> {
	Iterable<Mesa> findAll(Sort by);
	
	Mesa findByLocalAndSalaEscritorio_Id(String local, long salaEscritorioId);
}
