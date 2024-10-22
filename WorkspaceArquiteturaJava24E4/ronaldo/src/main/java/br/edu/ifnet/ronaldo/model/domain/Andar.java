package br.edu.ifnet.ronaldo.model.domain;

import java.util.List;

public class Andar {
	private int numero;
	private List<Sala> salas;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
}
