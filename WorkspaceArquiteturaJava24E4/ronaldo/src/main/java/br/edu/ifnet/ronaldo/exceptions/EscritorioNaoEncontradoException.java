package br.edu.ifnet.ronaldo.exceptions;

public class EscritorioNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EscritorioNaoEncontradoException(String message) {
		super(message);
	}
}
