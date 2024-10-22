package br.edu.ifnet.ronaldo.model.domain;

import java.util.Date;
import java.util.List;

public class SalaFesta extends Sala{
	private int tempoMaximoReserva;
	private Date horaMinimaReserva;
	private Date horaMaximaReserva;
	private List<Equipamento> equipamentos;
	
	public int getTempoMaximoReserva() {
		return tempoMaximoReserva;
	}
	public void setTempoMaximoReserva(int tempoMaximoReserva) {
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
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
}
