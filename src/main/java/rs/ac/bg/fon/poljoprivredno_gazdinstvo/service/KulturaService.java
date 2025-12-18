package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.KulturaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.KulturaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.KulturaRepository;

@Service
public class KulturaService {

	private final KulturaRepository kulturaRepository;
	private final KulturaMapper kulturaMapper;

	@Autowired
	public KulturaService(KulturaRepository kulturaRepository, KulturaMapper kulturaMapper) {
		this.kulturaRepository = kulturaRepository;
		this.kulturaMapper = kulturaMapper;
	}

	public List<KulturaDto> findAll() {
		return kulturaRepository.findAll().stream().map(kulturaMapper::toDto).toList();
	}

	public KulturaDto findById(Long id) {
		return kulturaRepository.findById(id).map(kulturaMapper::toDto).orElse(null);
	}

	public KulturaDto save( KulturaDto dto) {
		Kultura kultura = kulturaMapper.toEntity(dto);
		Kultura saved = kulturaRepository.save(kultura);

		return kulturaMapper.toDto(saved);
	}

	public KulturaDto update(Long id,  KulturaDto dto) {
		return kulturaRepository.findById(id).map(existing -> {
			existing.setNaziv(dto.getNaziv());
			existing.setSorta(dto.getSorta());

			Kultura saved = kulturaRepository.save(existing);
			return kulturaMapper.toDto(saved);
		}).orElse(null);
	}
	
	public void delete(Long id) {
		var kultura=kulturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
	            "Parcela sa id=" + id + " ne postoji"
	        ));
		
		kulturaRepository.delete(kultura);
		
	}
	
}
