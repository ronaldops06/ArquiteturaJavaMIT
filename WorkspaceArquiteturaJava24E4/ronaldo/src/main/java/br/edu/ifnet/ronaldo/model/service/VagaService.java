package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.EscritorioNaoEncontradoException;
import br.edu.ifnet.ronaldo.exceptions.VagaNaoEncontradaException;
import br.edu.ifnet.ronaldo.model.domain.Vaga;
import br.edu.ifnet.ronaldo.model.repository.VagaRepository;

@Service
public class VagaService {
	@Autowired
	private VagaRepository vagaRespository;
	
	public Vaga incluir(Vaga vaga) {
		return vagaRespository.save(vaga);
	}
	
	public Vaga alterar(Vaga vaga) {
		
		if (!vagaRespository.existsById(vaga.getId())) {
			throw new VagaNaoEncontradaException(Constants.MSG_NOT_FOUND);
		}
		
		return vagaRespository.save(vaga);
	}
	
	public boolean excluir(Integer id) {
		vagaRespository.deleteById(id);
		
		return true;
	}
	
	public Vaga obterPorId(Integer id) {
		return vagaRespository.findById(id).orElse(null);
	}
	
	public Collection<Vaga> obterLista(){
		return (Collection<Vaga>) vagaRespository.findAll(Sort.by(Sort.Order.asc("codigo")));
	}	
}
