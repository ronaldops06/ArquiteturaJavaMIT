package br.edu.ifnet.ronaldo.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifnet.ronaldo.model.domain.Reserva;

@Repository
public interface ReservaRepository  extends CrudRepository<Reserva, Integer>{
	Iterable<Reserva> findByPessoa_Id(long pessoaId, Sort by);
	
	Iterable<Reserva> findBySala_Id(long salaId, Sort by);
	
	Iterable<Reserva> findByMesa_Id(long mesaId, Sort by);
	
	Iterable<Reserva> findByVaga_Id(long vagaId, Sort by);
}
