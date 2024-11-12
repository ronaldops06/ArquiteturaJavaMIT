package br.edu.ifnet.ronaldo.model.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TSALA_AUDITORIO")
public class SalaAuditorio extends Sala{
	
	private Integer tempoMaximoReserva;
	private Date horaMinimaReserva;
	private Date horaMaximaReserva;
	
	@ManyToMany
    @JoinTable(
        name = "TSALA_AUDITORIO_EQUIP", 
        joinColumns = @JoinColumn(name = "sala_auditorio_id"), 
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
	private List<Equipamento> equipamentos;
	
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
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
}
