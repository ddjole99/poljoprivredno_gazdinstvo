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

/**
 * REST kontroler za autentifikaciju i registraciju administratora.
 * <p>
 * Ovaj kontroler obezbeđuje API krajnje tačke za:
 * <ul>
 *   <li>registraciju administratora</li>
 *   <li>prijavu administratora i izdavanje JWT tokena</li>
 *   <li>validaciju JWT tokena</li>
 * </ul>
 * </p>
 *
 * <p>
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/auth}.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService
 * @see org.springframework.security.authentication.AuthenticationManager
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AdministratorService administratorService;
	
	 /**
     * Spring Security komponenta za autentifikaciju korisnika.
     */
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	/**
     * Registruje novog administratora u sistemu.
     * <p>
     * U slučaju uspeha, vraća {@code 201 Created} i {@code Location} zaglavlje
     * koje pokazuje na resurs kreiranog administratora.
     * </p>
     *
     * @param req        zahtev za kreiranje administratora
     * @param uriBuilder builder za kreiranje URI-ja kreiranog resursa
     * @return {@link ResponseEntity} sa kreiranim {@link AdministratorDto},
     *         HTTP statusom {@code 201 Created} i zaglavljem {@code Location}
     */
	@PostMapping("/register")
	public ResponseEntity<AdministratorDto> create(@Valid @RequestBody AdministratorCreateRequest req,
			UriComponentsBuilder uriBuilder) {

		var created = administratorService.create(req);
		var uri = uriBuilder.path("/api/administratori/{id}").buildAndExpand(created.getAdministratorID()).toUri();

		return ResponseEntity.created(uri).body(created);
	}

	/**
     * Prijavljuje administratora i vraća JWT token.
     * <p>
     * Metoda vrši autentifikaciju korišćenjem email adrese i lozinke.
     * Ako je autentifikacija uspešna, generiše se JWT token koji klijent
     * koristi za autorizaciju narednih zahteva.
     * </p>
     *
     * @param req zahtev za prijavu administratora (email i lozinka)
     * @return {@link ResponseEntity} sa {@link JwtResponse} i HTTP statusom {@code 200 OK}
     *
     * @throws org.springframework.security.core.AuthenticationException
     *         ako autentifikacija nije uspešna (npr. pogrešni kredencijali)
     */
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
