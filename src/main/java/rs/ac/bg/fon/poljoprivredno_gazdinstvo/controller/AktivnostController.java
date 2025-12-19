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
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AktivnostDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AktivnostService;

/**
 * REST kontroler za upravljanje aktivnostima.
 * <p>
 * Ovaj kontroler obezbeđuje REST API krajnje tačke za:
 * <ul>
 *   <li>prikaz svih aktivnosti</li>
 *   <li>prikaz aktivnosti po identifikatoru</li>
 *   <li>kreiranje nove aktivnosti</li>
 *   <li>ažuriranje postojeće aktivnosti</li>
 *   <li>brisanje aktivnosti</li>
 * </ul>
 * </p>
 *
 * <p>
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/aktivnosti}.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AktivnostService
 */
@RestController
@RequestMapping("/api/aktivnosti")
@AllArgsConstructor
public class AktivnostController {

	private final AktivnostService aktivnostService;

	 /**
     * Vraća listu svih aktivnosti u sistemu.
     *
     * @return {@link ResponseEntity} sa listom {@link AktivnostDto} objekata
     *         i HTTP statusom {@code 200 OK}
     */
	@GetMapping
	public ResponseEntity<List<AktivnostDto>> getAll() {
		return new ResponseEntity<>(aktivnostService.findAll(), HttpStatus.OK);
	}

	 /**
     * Vraća aktivnost na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator aktivnosti
     * @return {@link ResponseEntity} sa {@link AktivnostDto} ako aktivnost postoji
     *         ili HTTP status {@code 404 Not Found} ako ne postoji
     */
	@GetMapping("/{id}")
	public ResponseEntity<AktivnostDto> getById(@PathVariable Long id) {
		var aktivnostDto = aktivnostService.findById(id);

		if (aktivnostDto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(aktivnostDto);
	}

	/**
     * Kreira novu aktivnost u sistemu.
     *
     * @param dto DTO objekat sa podacima o aktivnosti
     * @return {@link ResponseEntity} sa kreiranom {@link AktivnostDto}
     *         i HTTP statusom {@code 201 Created}
     */
	@PostMapping
	public ResponseEntity<AktivnostDto> create(@Valid @RequestBody AktivnostDto dto) {
		AktivnostDto created = aktivnostService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	/**
     * Ažurira postojeću aktivnost.
     *
     * @param id  jedinstveni identifikator aktivnosti
     * @param dto DTO objekat sa izmenjenim podacima o aktivnosti
     * @return {@link ResponseEntity} sa ažuriranom {@link AktivnostDto}
     *         i HTTP statusom {@code 200 OK}
     */
	@PutMapping("/{id}")
	public ResponseEntity<AktivnostDto> update(@Valid @RequestBody AktivnostDto dto, @PathVariable Long id) {
		return ResponseEntity.ok(aktivnostService.update(id, dto));
	}

	/**
     * Briše aktivnost iz sistema.
     *
     * @param id jedinstveni identifikator aktivnosti
     * @return {@link ResponseEntity} bez tela odgovora i HTTP statusom {@code 200 OK}
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		aktivnostService.delete(id);
		return ResponseEntity.ok().build();

	}

}
