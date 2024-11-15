package br.edu.ifnet.ronaldo.exceptions;

public class MesaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MesaNaoEncontradaException(String message) {
		super(message);
	}
}