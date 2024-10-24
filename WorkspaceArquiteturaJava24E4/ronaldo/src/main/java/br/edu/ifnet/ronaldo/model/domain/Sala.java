package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import jakarta.persistence.Transient;

@Entity
@Table(name = "TSALA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private int capacidade;
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "andar_id")
	private Andar andar;
	
	@Enumerated(EnumType.STRING)
	private TipoSala tipo;
	
	@ManyToMany
    @JoinTable(
        name = "TSALA_FUNCAO",
        joinColumns = @JoinColumn(name = "sala_id"), 
        inverseJoinColumns = @JoinColumn(name = "funcao_id")
    )
	private List<Funcao> funcoes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Andar getAndar() {
		return andar;
	}
	public void setAndar(Andar andar) {
		this.andar = andar;
	}
	public TipoSala getTipo() {
		return tipo;
	}
	public void setTipo(TipoSala tipo) {
		this.tipo = tipo;
	}
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}	
}
