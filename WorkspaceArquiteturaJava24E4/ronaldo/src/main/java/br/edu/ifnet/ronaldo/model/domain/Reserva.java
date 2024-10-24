package br.edu.ifnet.ronaldo.model.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRESERVA")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Pessoa pessoa;
	private Mesa mesa;
	private Sala sala;
	private Vaga vaga;
	private Date DataInicio;
	private Date DataFim;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	public Date getDataInicio() {
		return DataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		DataInicio = dataInicio;
	}
	public Date getDataFim() {
		return DataFim;
	}
	public void setDataFim(Date dataFim) {
		DataFim = dataFim;
	}
}
