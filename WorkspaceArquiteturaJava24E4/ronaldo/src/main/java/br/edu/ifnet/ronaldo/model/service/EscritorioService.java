package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.EscritorioNaoEncontradoException;
import br.edu.ifnet.ronaldo.model.domain.Endereco;
import br.edu.ifnet.ronaldo.model.domain.Escritorio;
import br.edu.ifnet.ronaldo.model.repository.EscritorioRepository;

@Service
public class EscritorioService {

	@Autowired
	private EscritorioRepository escritorioRepository;
	@Autowired
	private EnderecoService enderecoService;
	
	public Escritorio incluir(Escritorio escritorio) {
		
		Endereco endereco = enderecoService.incluir(escritorio.getEndereco());
		escritorio.setEndereco(endereco);
		
		return escritorioRepository.save(escritorio);
	}
	
	public Escritorio alterar(Escritorio escritorio) {
		
		if (!escritorioRepository.existsById(escritorio.getId())) {
			throw new EscritorioNaoEncontradoException(Constants.MSG_NOT_FOUND);
		}
				
		return escritorioRepository.save(escritorio);
	}
	
	public boolean excluir(Integer id) {
		escritorioRepository.deleteById(id);
		
		return true;
	}
	
	public Escritorio obterPorId(Integer id) {
		return escritorioRepository.findById(id).orElse(null);
	}
	
	public Collection<Escritorio> obterLista(){
		return (Collection<Escritorio>) escritorioRepository.findAll(Sort.by(Sort.Order.asc("nome")));
	}	
	
	public Escritorio findByEndereco(String cep, int numero) {
		return escritorioRepository.findByEndereco_CepAndEndereco_Numero(cep, numero);
	}
}
