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

@AllArgsConstructor
@RestController
@RequestMapping("/api/administratori")
public class AdministratorController {

	private final AdministratorService administratorService;
	
	@GetMapping
	public ResponseEntity<List<AdministratorDto>> getAll() {
		return ResponseEntity.ok(administratorService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdministratorDto> getById(@PathVariable Long id) {
		var dto = administratorService.findById(id);

		if (dto == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(dto);
	}


	@PutMapping("/{id}")
	public ResponseEntity<AdministratorDto> update(@PathVariable Long id,
			@Valid @RequestBody AdministratorUpdateRequest req) {
		var updated = administratorService.update(req, id);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		administratorService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/change-password")
	public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest req) {
		
		administratorService.changePassword(id,req);
		
		return ResponseEntity.ok().build();
	}

}
