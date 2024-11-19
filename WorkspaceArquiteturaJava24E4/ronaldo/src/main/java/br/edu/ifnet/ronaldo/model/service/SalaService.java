package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.SalaNaoEncontradaException;
import br.edu.ifnet.ronaldo.model.domain.Sala;
import br.edu.ifnet.ronaldo.model.repository.SalaRepository;

public abstract class SalaService<T extends Sala> {
	
	@Autowired
	protected AndarService andarService;
	
	protected SalaRepository<T> salaRepository;
	
	public SalaService(SalaRepository<T> salaRepository) {
		this.salaRepository = salaRepository;
	}

	public T incluir(T sala) {

		return salaRepository.save(sala);
	}
	
	public T alterar(T sala) {
		
		if (!salaRepository.existsById(sala.getId())) {
			throw new SalaNaoEncontradaException(Constants.MSG_NOT_FOUND);
		}
		
		return salaRepository.save(sala);
	}

	public boolean excluir(Integer id) {
		salaRepository.deleteById(id);
		
		return true;
	}
	
	public T obterPorId(Integer id) {
		return salaRepository.findById(id).orElse(null);
	}
	
	public Collection<T> obterLista(){
		return (Collection<T>) salaRepository.findAll(Sort.by(Sort.Order.asc("nome")));
	}
	
	public T findByNomeAndAndar(String nome, Integer andarId) {
		return salaRepository.findByNomeAndAndar_Id(nome, andarId);
	}
}
