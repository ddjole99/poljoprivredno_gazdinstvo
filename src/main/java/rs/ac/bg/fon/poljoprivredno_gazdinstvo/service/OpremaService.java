package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.OpremaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.OpremaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository;

@AllArgsConstructor
@Service
public class OpremaService {

	private final OpremaRepository opremaRepository;
	private final OpremaMapper opremaMapper;
	
	public List<OpremaDto> findAll() {
		return opremaRepository.findAll().stream().map(opremaMapper::toDto).toList();
	}

	public OpremaDto findById(Long id) {
		return opremaRepository.findById(id).map(opremaMapper::toDto).orElse(null);
	}

	
}
