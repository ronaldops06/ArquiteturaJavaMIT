package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.SalaAuditorio;
import br.edu.ifnet.ronaldo.model.domain.SalaEscritorio;

@Repository
public interface SalaEscritorioRepository extends CrudRepository<SalaEscritorio, Integer>, SalaRepository<SalaEscritorio>{
	SalaEscritorio findByNomeAndAndar_Id(String nome, long andarId);
	
	Iterable<SalaEscritorio> findAll(Sort by);
}
