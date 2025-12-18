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

@RestController
@RequestMapping("/api/parcele")

public class ParcelaController {
	
	private final ParcelaService parcelaService;
	
	
	public ParcelaController(ParcelaService parcelaService) {
		this.parcelaService = parcelaService;
	}

	@GetMapping
	public ResponseEntity<List<ParcelaDto>> getAll(){
		return new ResponseEntity<List<ParcelaDto>>(parcelaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ParcelaDto> getByid(@PathVariable Long id){
		var parcelaDto=parcelaService.findById(id);
		
		if(parcelaDto==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(parcelaDto);
	}
	
	@PostMapping
	public ResponseEntity<ParcelaDto> createParcela(@Valid @RequestBody ParcelaDto dto){
		ParcelaDto created = parcelaService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ParcelaDto> updateParcela(@Valid @RequestBody ParcelaDto dto, @PathVariable Long id){
		ParcelaDto updated=parcelaService.update(dto, id);
		
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParcela(@PathVariable Long id){
		parcelaService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}
