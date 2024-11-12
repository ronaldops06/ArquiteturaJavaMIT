package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.AndarNaoEncontradoException;
import br.edu.ifnet.ronaldo.exceptions.EscritorioNaoEncontradoException;
import br.edu.ifnet.ronaldo.model.domain.Andar;
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.repository.AndarRepository;
import br.edu.ifnet.ronaldo.model.repository.EscritorioRepository;

@Service
public class AndarService {
	
	@Autowired
	private AndarRepository andarRepository;
	@Autowired
	private EscritorioRepository escritorioRepository;
	
	public Andar incluir(Andar andar) {
		return andarRepository.save(andar);
	}
	
	public Andar alterar(Andar andar) {
		
		if (!andarRepository.existsById(andar.getId())) {
			throw new AndarNaoEncontradoException(Constants.MSG_NOT_FOUND);
		}
		
		return andarRepository.save(andar);
	}
	
	public boolean excluir(Integer id) {
		andarRepository.deleteById(id);
		
		return true;
	}
	
	public Andar obterPorId(Integer id) {
		return andarRepository.findById(id).orElse(null);
	}
	
	public Collection<Andar> obterLista(){
		return (Collection<Andar>) andarRepository.findAll(Sort.by(Sort.Order.asc("numero")));
	}
	
	public Andar findByNumeroAndEscritorio(int numero, long cepEndereco, int numeroEndereco) {
		Escritorio escritorio = escritorioRepository.findByEndereco_CepAndEndereco_Numero(cepEndereco, numeroEndereco);
		return andarRepository.findByNumeroAndEscritorio_Id(numero, escritorio.getId());
	}
}
