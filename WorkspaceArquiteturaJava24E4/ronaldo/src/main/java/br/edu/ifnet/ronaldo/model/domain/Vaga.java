package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TVAGA")
public class Vaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String codigo;
	private Boolean possuiCarregador;
	private Boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private TipoVaga tipo;
	
	@ManyToOne
	@JoinColumn(name = "sala_garagem_id")
	private SalaGaragem salaGaragem;
	
	@ManyToOne
    @JoinTable(
        name = "TVAGA_FUNCAO", 
        joinColumns = @JoinColumn(name = "vaga_id"), 
        inverseJoinColumns = @JoinColumn(name = "funcao_id")
    )
	private List<Funcao> funcoes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Boolean getPossuiCarregador() {
		return possuiCarregador;
	}
	public void setPossuiCarregador(Boolean possuiCarregador) {
		this.possuiCarregador = possuiCarregador;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public TipoVaga getTipo() {
		return tipo;
	}
	public void setTipo(TipoVaga tipo) {
		this.tipo = tipo;
	}
	
	public SalaGaragem getSalaGaragem() {
		return salaGaragem;
	}
	public void setSalaGaragem(SalaGaragem salaGaragem) {
		this.salaGaragem = salaGaragem;
	}
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	
}
