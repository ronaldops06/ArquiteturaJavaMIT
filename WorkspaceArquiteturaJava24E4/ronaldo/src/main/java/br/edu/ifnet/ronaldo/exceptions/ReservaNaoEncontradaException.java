package br.edu.ifnet.ronaldo.exceptions;

public class ReservaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReservaNaoEncontradaException(String message) {
		super(message);
	}
}
