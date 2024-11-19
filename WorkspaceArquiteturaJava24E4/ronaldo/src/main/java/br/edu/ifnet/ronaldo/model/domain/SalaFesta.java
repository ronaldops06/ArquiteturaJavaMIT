package br.edu.ifnet.ronaldo.model.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "TSALA_FESTA")
public class SalaFesta extends Sala{
	
	private Integer tempoMaximoReserva;
	private Date horaMinimaReserva;
	private Date horaMaximaReserva;
	
	public Integer getTempoMaximoReserva() {
		return tempoMaximoReserva;
	}
	public void setTempoMaximoReserva(Integer tempoMaximoReserva) {
		this.tempoMaximoReserva = tempoMaximoReserva;
	}
	public Date getHoraMinimaReserva() {
		return horaMinimaReserva;
	}
	public void setHoraMinimaReserva(Date horaMinimaReserva) {
		this.horaMinimaReserva = horaMinimaReserva;
	}
	public Date getHoraMaximaReserva() {
		return horaMaximaReserva;
	}
	public void setHoraMaximaReserva(Date horaMaximaReserva) {
		this.horaMaximaReserva = horaMaximaReserva;
	}
}
