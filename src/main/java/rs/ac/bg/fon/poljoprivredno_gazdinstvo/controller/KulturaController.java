package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.KulturaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.KulturaService;

/**
 * REST kontroler za upravljanje poljoprivrednim kulturama.
 * 
 * Ovaj kontroler obezbedjuje REST API krajnje tacke za:
 * <ul>
 *   <li>prikaz svih kultura</li>
 *   <li>prikaz kulture po identifikatoru</li>
 *   <li>kreiranje nove kulture</li>
 *   <li>azuriranje postojece kulture</li>
 *   <li>brisanje kulture</li>
 * </ul>
 * 
 *
 * 
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/kulture}.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.KulturaService
 */
@RestController
@RequestMapping("/api/kulture")
public class KulturaController {

	private KulturaService kulturaService;

	@Autowired
	public KulturaController(KulturaService kulturaService) {
		super();
		this.kulturaService = kulturaService;
	}

	/**
     * Vraća listu svih poljoprivrednih kultura u sistemu.
     *
     * @return {@link ResponseEntity} sa listom {@link KulturaDto} objekata
     *         i HTTP statusom {@code 200 OK}
     */
	@GetMapping()
	public ResponseEntity<List<KulturaDto>> getAll() {
		return new ResponseEntity<>(kulturaService.findAll(), HttpStatus.OK);
	}

	 /**
     * Vraća kulturu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator kulture
     * @return {@link ResponseEntity} sa {@link KulturaDto} ako kultura postoji,
     *         ili HTTP status {@code 404 Not Found} ako ne postoji
     */
	@GetMapping("/{id}")
	public ResponseEntity<KulturaDto> getById(@PathVariable Long id) {
		KulturaDto kulturaDto = kulturaService.findById(id);

		if (kulturaDto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(kulturaDto);
	}

	/**
     * Kreira novu poljoprivrednu kulturu.
     *
     * @param dto DTO objekat sa podacima o kulturi
     * @return {@link ResponseEntity} sa kreiranom {@link KulturaDto}
     *         i HTTP statusom {@code 201 Created}
     */
	@PostMapping()
	public ResponseEntity<KulturaDto> create(@RequestBody @Valid KulturaDto dto) {
		KulturaDto created = kulturaService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	/**
     * Ažurira postojeću poljoprivrednu kulturu.
     *
     * @param id  jedinstveni identifikator kulture
     * @param dto DTO objekat sa izmenjenim podacima o kulturi
     * @return {@link ResponseEntity} sa ažuriranom {@link KulturaDto}
     *         i HTTP statusom {@code 200 OK}, ili {@code 404 Not Found}
     *         ako kultura ne postoji
     */
	@PutMapping("/{id}")
	public ResponseEntity<KulturaDto> update(@PathVariable Long id, @RequestBody @Valid KulturaDto dto) {
		KulturaDto updated = kulturaService.update(id, dto);
		if (updated == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(updated);
	}

	/**
     * Brise poljoprivrednu kulturu iz sistema.
     *
     * @param id jedinstveni identifikator kulture
     * @return {@link ResponseEntity} bez tela odgovora i HTTP statusom {@code 200 OK}
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		kulturaService.delete(id);

		return new ResponseEntity(HttpStatus.OK);

	}
}
