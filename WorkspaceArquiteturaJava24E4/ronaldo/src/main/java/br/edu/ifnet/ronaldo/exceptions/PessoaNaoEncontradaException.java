package br.edu.ifnet.ronaldo.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String message) {
		super(message);
	}
}
