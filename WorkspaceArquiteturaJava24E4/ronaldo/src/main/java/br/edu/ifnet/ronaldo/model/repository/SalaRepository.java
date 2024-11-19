package br.edu.ifnet.ronaldo.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort;

public interface SalaRepository<T>{
	T findByNomeAndAndar_Id(String nome, long andarId);
	
	Iterable<T> findAll(Sort by);
	
	T save(T sala);
	
	boolean existsById(Integer lojaId);
	
	void deleteById(Integer lojaId);
	
	Optional<T> findById(Integer lojaId);
}
