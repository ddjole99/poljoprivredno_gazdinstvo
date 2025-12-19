package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.SetvaService;

/**
 * REST kontroler za upravljanje setvama.
 * <p>
 * Setva predstavlja centralni poslovni entitet sistema i povezuje
 * parcelu, kulturu, administratora i skup izvršenih aktivnosti
 * (stavki setve).
 * </p>
 *
 * <p>
 * Ovaj kontroler obezbeđuje REST API krajnje tačke za:
 * <ul>
 *   <li>prikaz svih setvi</li>
 *   <li>prikaz pojedinačne setve po identifikatoru</li>
 *   <li>kreiranje nove setve sa stavkama</li>
 *   <li>ažuriranje postojeće setve</li>
 *   <li>brisanje setve</li>
 * </ul>
 * </p>
 *
 * <p>
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/setve}.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.SetvaService
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/setve")
public class SetvaController {

	private final SetvaService setvaService;

	/**
     * Vraća listu svih setvi u sistemu.
     *
     * @return {@link ResponseEntity} sa listom {@link SetvaDto} objekata
     *         i HTTP statusom {@code 200 OK}
     */
	@GetMapping
	public ResponseEntity<List<SetvaDto>> getAll() {

		return ResponseEntity.ok(setvaService.findAll());

	}

	  /**
     * Vraća setvu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator setve
     * @return {@link ResponseEntity} sa {@link SetvaDto} ako setva postoji,
     *         ili HTTP status {@code 404 Not Found} ako ne postoji
     */
	@GetMapping("/{id}")
	public ResponseEntity<SetvaDto> getById(@PathVariable Long id) {

		SetvaDto dto = setvaService.findById(id);
		if (dto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(dto);
	}

	/**
     * Kreira novu setvu u sistemu.
     * <p>
     * Prilikom kreiranja setve mogu se proslediti i stavke setve
     * koje predstavljaju aktivnosti izvršene u okviru setve.
     * </p>
     *
     * @param setvaDto DTO objekat sa podacima o setvi i njenim stavkama
     * @return {@link ResponseEntity} sa kreiranom {@link SetvaDto}
     *         i HTTP statusom {@code 201 Created}
     */
	@PostMapping
	public ResponseEntity<SetvaDto> create(@Valid @RequestBody SetvaDto setvaDto) {
		SetvaDto created = setvaService.save(setvaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	 /**
     * Ažurira postojeću setvu.
     *
     * @param id       jedinstveni identifikator setve
     * @param setvaDto DTO objekat sa izmenjenim podacima o setvi
     * @return {@link ResponseEntity} sa ažuriranom {@link SetvaDto}
     *         i HTTP statusom {@code 200 OK}, ili {@code 404 Not Found}
     *         ako setva ne postoji
     */
	@PutMapping("/{id}")
	public ResponseEntity<SetvaDto> update(@Valid @RequestBody SetvaDto setvaDto, @PathVariable Long id) {
		SetvaDto updated = setvaService.update(setvaDto, id);
		if (updated == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	/**
     * Briše setvu iz sistema.
     *
     * @param id jedinstveni identifikator setve
     * @return {@link ResponseEntity} bez tela odgovora i HTTP statusom {@code 200 OK}
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		setvaService.delete(id);
	    return ResponseEntity.ok().build();
	}
}
