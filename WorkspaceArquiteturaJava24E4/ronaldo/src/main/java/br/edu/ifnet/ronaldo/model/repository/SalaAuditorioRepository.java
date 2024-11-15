package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.SalaAuditorio;
import br.edu.ifnet.ronaldo.model.domain.SalaGaragem;

@Repository
public interface SalaAuditorioRepository extends CrudRepository<SalaAuditorio, Integer>, SalaRepository<SalaAuditorio>{
	SalaAuditorio findByNomeAndAndar_Id(String nome, long andarId);
	
	Iterable<SalaAuditorio> findAll(Sort by);
}
