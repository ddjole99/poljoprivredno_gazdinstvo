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

@RestController
@RequestMapping("/api/kulture")
public class KulturaController {

	private KulturaService kulturaService;

	@Autowired
	public KulturaController(KulturaService kulturaService) {
		super();
		this.kulturaService = kulturaService;
	}

	@GetMapping()
	public ResponseEntity<List<KulturaDto>> getAll() {
		return new ResponseEntity<>(kulturaService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<KulturaDto> getById(@PathVariable Long id) {
		KulturaDto kulturaDto = kulturaService.findById(id);

		if (kulturaDto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(kulturaDto);
	}

	@PostMapping()
	public ResponseEntity<KulturaDto> create(@RequestBody @Valid KulturaDto dto) {
		KulturaDto created = kulturaService.save(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KulturaDto> update(@PathVariable Long id, @RequestBody @Valid KulturaDto dto) {
		KulturaDto updated = kulturaService.update(id, dto);
		if (updated == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		kulturaService.delete(id);

		return new ResponseEntity(HttpStatus.OK);

	}
}
