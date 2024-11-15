package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TMESA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="O local da mesa é obrigatório")
	@Size(min=2, max=20, message="O local deve ter entre 2 e 20 caracteres")
	private String local;
	private Boolean Ativo;
	
	@ManyToOne
	@JoinColumn(name = "sala_escritorio_id")
	@JsonBackReference
	private SalaEscritorio salaEscritorio;
		
	@ManyToMany
    @JoinTable(
        name = "TMESA_EQUIPAMENTO", 
        joinColumns = @JoinColumn(name = "mesa_id"), 
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
	private List<Equipamento> equipamentos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Boolean getAtivo() {
		return Ativo;
	}
	public void setAtivo(Boolean ativo) {
		Ativo = ativo;
	}
	
	public SalaEscritorio getSalaEscritorio() {
		return salaEscritorio;
	}
	public void setSalaEscritorio(SalaEscritorio salaEscritorio) {
		this.salaEscritorio = salaEscritorio;
	}
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
}
