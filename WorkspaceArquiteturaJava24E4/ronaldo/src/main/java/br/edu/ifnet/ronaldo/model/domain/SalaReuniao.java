package br.edu.ifnet.ronaldo.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TSALA_REUNIAO")
public class SalaReuniao extends Sala{
	private Integer tempoMaximoReserva;
	
	public Integer getTempoMaximoReserva() {
		return tempoMaximoReserva;
	}

	public void setTempoMaximoReserva(Integer tempoMaximoReserva) {
		this.tempoMaximoReserva = tempoMaximoReserva;
	}
}
