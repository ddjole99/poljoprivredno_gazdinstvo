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

@RestController
@RequestMapping("/api/aktivnosti")
@AllArgsConstructor
public class AktivnostController {

	private final AktivnostService aktivnostService;

	@GetMapping
	public ResponseEntity<List<AktivnostDto>> getAll() {
		return new ResponseEntity<>(aktivnostService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AktivnostDto> getById(@PathVariable Long id) {
		var aktivnostDto = aktivnostService.findById(id);

		if (aktivnostDto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(aktivnostDto);
	}

	@PostMapping
	public ResponseEntity<AktivnostDto> create(@Valid @RequestBody AktivnostDto dto) {
		AktivnostDto created = aktivnostService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AktivnostDto> update(@Valid @RequestBody AktivnostDto dto, @PathVariable Long id) {
		return ResponseEntity.ok(aktivnostService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		aktivnostService.delete(id);
		return ResponseEntity.ok().build();

	}

}
