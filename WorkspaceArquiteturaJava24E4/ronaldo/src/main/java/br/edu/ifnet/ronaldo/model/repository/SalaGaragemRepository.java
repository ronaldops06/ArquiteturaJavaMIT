package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;

@Repository
public interface SalaGaragemRepository extends CrudRepository<SalaGaragem, Integer>, SalaRepository<SalaGaragem> {
	Iterable<SalaGaragem> findAll(Sort by);
}
