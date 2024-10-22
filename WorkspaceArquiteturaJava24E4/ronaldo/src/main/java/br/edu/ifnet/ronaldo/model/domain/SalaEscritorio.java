package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

public class SalaEscritorio extends Sala{
	private List<Mesa> mesas;

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}
}
