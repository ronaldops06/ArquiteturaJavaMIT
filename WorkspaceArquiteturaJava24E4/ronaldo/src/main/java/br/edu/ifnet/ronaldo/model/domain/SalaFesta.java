package br.edu.ifnet.ronaldo.model.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TSALA_ESCRITORIO")
public class SalaFesta extends Sala{
	
	private Integer tempoMaximoReserva;
	private Date horaMinimaReserva;
	private Date horaMaximaReserva;
	
	@ManyToOne
    @JoinTable(
        name = "TSALA_ESCRITORIO_EQUIP", 
        joinColumns = @JoinColumn(name = "sala_escritorio_id"), 
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
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
