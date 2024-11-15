package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.SalaFesta;
import br.edu.ifnet.ronaldo.model.domain.SalaReuniao;

@Repository
public interface SalaReuniaoRepository extends CrudRepository<SalaReuniao, Integer>, SalaRepository<SalaReuniao>{
	SalaReuniao findByNomeAndAndar_Id(String nome, long andarId);
	
	Iterable<SalaReuniao> findAll(Sort by);
}
