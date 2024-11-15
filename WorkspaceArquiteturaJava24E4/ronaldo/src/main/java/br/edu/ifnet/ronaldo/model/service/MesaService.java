package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.MesaNaoEncontradaException;
import br.edu.ifnet.ronaldo.model.domain.Mesa;
import br.edu.ifnet.ronaldo.model.repository.MesaRepository;

@Service
public class MesaService {
	@Autowired
	private MesaRepository mesaRepository;
	
	public Mesa incluir(Mesa mesa) {
		return mesaRepository.save(mesa);
	}
	
	public Mesa alterar(Mesa Mesa) {
		
		if (!mesaRepository.existsById(Mesa.getId())) {
			throw new MesaNaoEncontradaException(Constants.MSG_NOT_FOUND);
		}
		
		return mesaRepository.save(Mesa);
	}
	
	public boolean excluir(Integer id) {
		mesaRepository.deleteById(id);
		
		return true;
	}
	
	public Mesa obterPorId(Integer id) {
		return mesaRepository.findById(id).orElse(null);
	}
	
	public Collection<Mesa> obterLista(){
		return (Collection<Mesa>) mesaRepository.findAll(Sort.by(Sort.Order.asc("local")));
	}
}
