package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorUpdateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ChangePasswordRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AdministratorService;

/**
 * REST kontroler za upravljanje administratorima sistema.
 * 
 * Ovaj kontroler obezbedjuje REST API krajnje tacke za:
 * <ul>
 *   <li>prikaz svih administratora</li>
 *   <li>prikaz administratora po identifikatoru</li>
 *   <li>azuriranje podataka administratora</li>
 *   <li>brisanje administratora</li>
 *   <li>promenu lozinke administratora</li>
 * </ul>
 * 
 *
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/administratori}.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AdministratorService
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/administratori")
public class AdministratorController {

	private final AdministratorService administratorService;
	
	/**
     * Vraca listu svih administratora u sistemu.
     *
     * @return {@link ResponseEntity} sa listom {@link AdministratorDto} objekata
     *         i HTTP statusom {@code 200 OK}
     */
	@GetMapping
	public ResponseEntity<List<AdministratorDto>> getAll() {
		return ResponseEntity.ok(administratorService.findAll());
	}

	/**
     * Vraća administratora na osnovu njegovog identifikatora.
     *
     * @param id jedinstveni identifikator administratora
     * @return {@link ResponseEntity} sa {@link AdministratorDto} ako administrator postoji
     *         ili HTTP status {@code 404 Not Found} ako ne postoji
     */
	@GetMapping("/{id}")
	public ResponseEntity<AdministratorDto> getById(@PathVariable Long id) {
		var dto = administratorService.findById(id);

		if (dto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(dto);
	}


	/**
     * Ažurira podatke postojećeg administratora.
     *
     * @param id  jedinstveni identifikator administratora
     * @param req zahtev za izmenu administratora
     * @return {@link ResponseEntity} sa ažuriranim {@link AdministratorDto}
     *         i HTTP statusom {@code 200 OK}
     */
	@PutMapping("/{id}")
	public ResponseEntity<AdministratorDto> update(@PathVariable Long id,
			@Valid @RequestBody AdministratorUpdateRequest req) {
		var updated = administratorService.update(req, id);
		return ResponseEntity.ok(updated);
	}
	
	/**
     * Briše administratora iz sistema.
     *
     * @param id jedinstveni identifikator administratora
     * @return {@link ResponseEntity} bez tela odgovora i HTTP statusom {@code 200 OK}
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		administratorService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	/**
     * Menja lozinku administratora.
     * Stara lozinka se koristi za verifikaciju identiteta,
     * a nova lozinka se bezbedno hesira pre cuvanja.
     *
     * @param id  jedinstveni identifikator administratora
     * @param req zahtev za promenu lozinke
     * @return {@link ResponseEntity} bez tela odgovora i HTTP statusom {@code 200 OK}
     */
	@PostMapping("/{id}/change-password")
	public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest req) {
		
		administratorService.changePassword(id,req);
		
		return ResponseEntity.ok().build();
	}

}
