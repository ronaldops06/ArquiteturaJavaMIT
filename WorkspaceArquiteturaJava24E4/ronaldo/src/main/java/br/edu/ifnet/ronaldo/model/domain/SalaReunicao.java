package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

public class SalaReunicao extends Sala{
	private int tempoMaximoReserva;
	private List<Equipamento> equipamentos;

	
	public int getTempoMaximoReserva() {
		return tempoMaximoReserva;
	}

	public void setTempoMaximoReserva(int tempoMaximoReserva) {
		this.tempoMaximoReserva = tempoMaximoReserva;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
}
