package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import java.util.List;

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
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.ParcelaService;

/**
 * REST kontroler za upravljanje parcelama.
 * 
 * Ovaj kontroler obezbedjuje REST API krajnje tacke za:
 * <ul>
 *   <li>prikaz svih parcela</li>
 *   <li>prikaz parcele po identifikatoru</li>
 *   <li>kreiranje nove parcele</li>
 *   <li>azuriranje postojece parcele</li>
 *   <li>brisanje parcele</li>
 * </ul>
 * 
 *
 *
 * Svi zahtevi su mapirani pod osnovnom putanjom {@code /api/parcele}.
 *
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.ParcelaService
 */
@RestController
@RequestMapping("/api/parcele")

public class ParcelaController {
	
	private final ParcelaService parcelaService;
	
	
	public ParcelaController(ParcelaService parcelaService) {
		this.parcelaService = parcelaService;
	}

	/**
     * Vraća listu svih parcela u sistemu.
     *
     * @return {@link ResponseEntity} sa listom {@link ParcelaDto} objekata
     *         i HTTP statusom {@code 200 OK}
     */
	@GetMapping
	public ResponseEntity<List<ParcelaDto>> getAll(){
		return new ResponseEntity<List<ParcelaDto>>(parcelaService.findAll(), HttpStatus.OK);
	}
	
	/**
     * Vraća parcelu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator parcele
     * @return {@link ResponseEntity} sa {@link ParcelaDto} ako parcela postoji,
     *         ili HTTP status {@code 404 Not Found} ako ne postoji
     */
	@GetMapping("/{id}")
	public ResponseEntity<ParcelaDto> getByid(@PathVariable Long id){
		var parcelaDto=parcelaService.findById(id);
		
		if(parcelaDto==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(parcelaDto);
	}
	
	/**
     * Kreira novu parcelu u sistemu.
     *
     * @param dto DTO objekat sa podacima o parceli
     * @return {@link ResponseEntity} sa kreiranom {@link ParcelaDto}
     *         i HTTP statusom {@code 201 Created}
     */
	@PostMapping
	public ResponseEntity<ParcelaDto> createParcela(@Valid @RequestBody ParcelaDto dto){
		ParcelaDto created = parcelaService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	 /**
     * Ažurira postojeću parcelu.
     *
     * @param id  jedinstveni identifikator parcele
     * @param dto DTO objekat sa izmenjenim podacima o parceli
     * @return {@link ResponseEntity} sa azuriranom {@link ParcelaDto}
     *         i HTTP statusom {@code 200 OK}
     */
	@PutMapping("/{id}")
	public ResponseEntity<ParcelaDto> updateParcela(@Valid @RequestBody ParcelaDto dto, @PathVariable Long id){
		ParcelaDto updated=parcelaService.update(dto, id);
		
		return ResponseEntity.ok(updated);
	}

	 /**
     * Briše parcelu iz sistema.
     *
     * @param id jedinstveni identifikator parcele
     * @return {@link ResponseEntity} bez tela odgovora i HTTP statusom {@code 200 OK}
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParcela(@PathVariable Long id){
		parcelaService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}
