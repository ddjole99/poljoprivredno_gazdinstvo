package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound(EntityNotFoundException ex) {
		return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleStatusEx(ResponseStatusException ex) {
		return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
	}
	
	public ResponseEntity<Void> handleBadCredentialsException(){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
