package br.edu.ifnet.ronaldo.exceptions;

public class VagaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VagaNaoEncontradaException(String message) {
		super(message);
	}
}