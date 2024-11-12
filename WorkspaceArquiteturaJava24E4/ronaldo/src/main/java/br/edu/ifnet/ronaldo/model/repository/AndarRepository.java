package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifnet.ronaldo.model.domain.Andar;

@Repository
public interface AndarRepository extends CrudRepository<Andar, Integer> {
	Andar findByNumeroAndEscritorio_Id(int numero, long escritorioId);
	
	Iterable<Andar> findAll(Sort by);
}
