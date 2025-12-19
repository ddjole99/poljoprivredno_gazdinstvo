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
