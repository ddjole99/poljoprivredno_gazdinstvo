package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;

/**
 * Globalni handler za obradu izuzetaka u REST sloju aplikacije.
 * <p>
 * Ova klasa centralizuje obradu izuzetaka koji nastaju u kontrolerima
 * i servisnom sloju, i mapira ih na odgovarajuće HTTP odgovore
 * u standardizovanom JSON formatu.
 * </p>
 *
 * <p>
 * Svi odgovori o grešci imaju sledeću strukturu:
 * </p>
 * <pre>
 * {
 *   "message": "opis greške"
 * }
 * </pre>
 *
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleStatusEx(ResponseStatusException ex) {
		return ResponseEntity.status(ex.getStatusCode()).body(Map.of("message", ex.getMessage()));
	}
	
	public ResponseEntity<?> handleBadCredentialsException(){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mesage","Neispravni kredencijali"));
	}
}
