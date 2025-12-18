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

@AllArgsConstructor
@RestController
@RequestMapping("/api/setve")
public class SetvaController {

	private final SetvaService setvaService;

	@GetMapping
	public ResponseEntity<List<SetvaDto>> getAll() {

		return ResponseEntity.ok(setvaService.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<SetvaDto> getById(@PathVariable Long id) {

		SetvaDto dto = setvaService.findById(id);
		if (dto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<SetvaDto> create(@Valid @RequestBody SetvaDto setvaDto) {
		SetvaDto created = setvaService.save(setvaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SetvaDto> update(@Valid @RequestBody SetvaDto setvaDto, @PathVariable Long id) {
		SetvaDto updated = setvaService.update(setvaDto, id);
		if (updated == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		setvaService.delete(id);
	    return ResponseEntity.ok().build();
	}
}
