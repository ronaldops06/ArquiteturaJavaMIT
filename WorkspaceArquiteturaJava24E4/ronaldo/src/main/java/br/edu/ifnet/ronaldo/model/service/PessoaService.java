package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.PessoaNaoEncontradaException;
import br.edu.ifnet.ronaldo.model.domain.Pessoa;
import br.edu.ifnet.ronaldo.model.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa incluir(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa alterar(Pessoa pessoa) {
		
		if (!pessoaRepository.existsById(pessoa.getId())) {
			throw new PessoaNaoEncontradaException(Constants.MSG_NOT_FOUND);
		}
		
		return pessoaRepository.save(pessoa);
	}
	
	public boolean excluir(Integer id) {
		pessoaRepository.deleteById(id);
		
		return true;
	}
	
	public Pessoa obterPorId(Integer id) {
		return pessoaRepository.findById(id).orElse(null);
	}
	
	public Pessoa obterPorCpf(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}
	
	public Collection<Pessoa> obterLista(){
		return (Collection<Pessoa>) pessoaRepository.findAll(Sort.by(Sort.Order.asc("nome")));
	}
}
