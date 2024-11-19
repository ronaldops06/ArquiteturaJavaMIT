package br.edu.ifnet.ronaldo.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifnet.ronaldo.Constants;
import br.edu.ifnet.ronaldo.exceptions.ReservaNaoEncontradaException;
import br.edu.ifnet.ronaldo.model.domain.Reserva;
import br.edu.ifnet.ronaldo.model.domain.TipoReserva;
import br.edu.ifnet.ronaldo.model.repository.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	public Reserva incluir(Reserva reserva) {
		return reservaRepository.save(reserva);
	}
	
	public Reserva alterar(Reserva reserva) {
		
		if (!reservaRepository.existsById(reserva.getId())) {
			throw new ReservaNaoEncontradaException(Constants.MSG_NOT_FOUND);
		}
		
		return reservaRepository.save(reserva);
	}
	
	public boolean excluir(Integer id) {
		reservaRepository.deleteById(id);
		
		return true;
	}
	
	public Reserva obterPorId(Integer id) {
		return reservaRepository.findById(id).orElse(null);
	}
	
	public Collection<Reserva> obterPorPessoaId(Integer pessoaId){
		return (Collection<Reserva>) reservaRepository.findByPessoa_Id(pessoaId, Sort.by(Sort.Order.asc("dataInicio")));
	}
	
	public Collection<Reserva> obterPorTipoReserva(TipoReserva tipoReserva, Integer recursoId){
		Collection<Reserva> resultado = null;
		
		switch(tipoReserva) {
		case Sala:
			resultado = (Collection<Reserva>) reservaRepository.findBySala_Id(recursoId, Sort.by(Sort.Order.asc("dataInicio")));
			break;
		case Mesa:
			resultado = (Collection<Reserva>) reservaRepository.findByMesa_Id(recursoId, Sort.by(Sort.Order.asc("dataInicio")));
			break;
		case Vaga:
			resultado = (Collection<Reserva>) reservaRepository.findByVaga_Id(recursoId, Sort.by(Sort.Order.asc("dataInicio")));
			break;
		}
		
		return resultado;
	}
}
