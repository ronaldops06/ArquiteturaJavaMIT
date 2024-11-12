package br.edu.ifnet.ronaldo.exceptions;

public class AndarNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AndarNaoEncontradoException(String message) {
		super(message);
	}
}
