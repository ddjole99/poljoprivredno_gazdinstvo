package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorLoginRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.JwtResponse;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AdministratorService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AdministratorService administratorService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<AdministratorDto> create(@Valid @RequestBody AdministratorCreateRequest req,
			UriComponentsBuilder uriBuilder) {

		var created = administratorService.create(req);
		var uri = uriBuilder.path("/api/administratori/{id}").buildAndExpand(created.getAdministratorID()).toUri();

		return ResponseEntity.created(uri).body(created);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody AdministratorLoginRequest req) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

		var token = jwtService.generateToken(req.getEmail());
		return ResponseEntity.ok(new JwtResponse(token)); 
	}
	
	@PostMapping("/validate")
	public boolean validate(@RequestHeader("Authorization") String authHeader) {
		var token = authHeader.replace("Bearer ", "");
		return jwtService.validateToken(token);
	}
	
	

}
