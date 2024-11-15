package br.edu.ifnet.ronaldo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.ifnet.ronaldo.exceptions.AndarNaoEncontradoException;
import br.edu.ifnet.ronaldo.exceptions.EscritorioNaoEncontradoException;
import br.edu.ifnet.ronaldo.exceptions.MesaNaoEncontradaException;
import br.edu.ifnet.ronaldo.exceptions.SalaNaoEncontradaException;
import br.edu.ifnet.ronaldo.exceptions.VagaNaoEncontradaException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(EscritorioNaoEncontradoException.class)
	public ResponseEntity<Object> handleValidationExceptions(EscritorioNaoEncontradoException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AndarNaoEncontradoException.class)
	public ResponseEntity<Object> handleValidationExceptions(AndarNaoEncontradoException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SalaNaoEncontradaException.class)
	public ResponseEntity<Object> handleValidationExceptions(SalaNaoEncontradaException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(VagaNaoEncontradaException.class)
	public ResponseEntity<Object> handleValidationExceptions(VagaNaoEncontradaException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MesaNaoEncontradaException.class)
	public ResponseEntity<Object> handleValidationExceptions(MesaNaoEncontradaException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
