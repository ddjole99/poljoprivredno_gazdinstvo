package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.OpremaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.OpremaService;

/**
 * REST kontroler za upravljanje opremom.
 * <p>
 * Ovaj kontroler obezbeđuje REST API krajnje tačke za:
 * <ul>
 *   <li>prikaz kompletne liste opreme</li>
 *   <li>prikaz pojedinačne opreme po identifikatoru</li>
 * </ul>
 * </p>
 *
 * <p>
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/oprema}.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.OpremaService
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/oprema")
public class OpremaController {

	private final OpremaService opremaService;

	/**
     * Vraća listu sve dostupne opreme u sistemu.
     *
     * @return {@link ResponseEntity} sa listom {@link OpremaDto} objekata
     *         i HTTP statusom {@code 200 OK}
     */
	@GetMapping
	public ResponseEntity<List<OpremaDto>> getAll() {

		return new ResponseEntity<List<OpremaDto>>(opremaService.findAll(), HttpStatus.OK);

	}

	/**
     * Vraća opremu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator opreme
     * @return {@link ResponseEntity} sa {@link OpremaDto} ako oprema postoji,
     *         ili HTTP status {@code 404 Not Found} ako ne postoji
     */
	@GetMapping("/{id}")
	public ResponseEntity<OpremaDto> getById(@PathVariable Long id) {
		
		var opremaDto=opremaService.findById(id);
		
		if(opremaDto==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(opremaDto);
	}

}
