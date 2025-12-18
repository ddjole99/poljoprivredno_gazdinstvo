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

@AllArgsConstructor
@RestController
@RequestMapping("/api/oprema")
public class OpremaController {

	private final OpremaService opremaService;

	@GetMapping
	public ResponseEntity<List<OpremaDto>> getAll() {

		return new ResponseEntity<List<OpremaDto>>(opremaService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<OpremaDto> getById(@PathVariable Long id) {
		
		var opremaDto=opremaService.findById(id);
		
		if(opremaDto==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(opremaDto);
	}

}
