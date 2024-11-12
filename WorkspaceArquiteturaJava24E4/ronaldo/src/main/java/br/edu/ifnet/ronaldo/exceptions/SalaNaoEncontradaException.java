package br.edu.ifnet.ronaldo.exceptions;

public class SalaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SalaNaoEncontradaException(String message) {
		super(message);
	}
}
