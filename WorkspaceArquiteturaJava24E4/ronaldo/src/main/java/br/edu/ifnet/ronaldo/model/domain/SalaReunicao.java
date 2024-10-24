package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TSALA_REUNIAO")
public class SalaReunicao extends Sala{
	private Integer tempoMaximoReserva;
	
	@ManyToOne
    @JoinTable(
        name = "TSALA_REUNIAO_EQUIP", 
        joinColumns = @JoinColumn(name = "sala_reuniao_id"), 
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
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
