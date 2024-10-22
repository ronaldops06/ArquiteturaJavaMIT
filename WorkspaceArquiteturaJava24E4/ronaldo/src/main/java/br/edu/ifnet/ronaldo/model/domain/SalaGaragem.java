package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

public class SalaGaragem extends Sala{
	private List<Vaga> vagas;

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}
}
