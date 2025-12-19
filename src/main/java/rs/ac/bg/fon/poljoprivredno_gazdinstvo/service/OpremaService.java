package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.OpremaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.OpremaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository;

/**
 * Servisni sloj za upravljanje opremom u sistemu.
 * <p>
 * Ova klasa sadrži poslovnu logiku za:
 * <ul>
 *   <li>prikaz dostupne opreme</li>
 *   <li>pribavljanje pojedinačne opreme po identifikatoru</li>
 * </ul>
 * </p>
 *
 * <p>
 * Servis koristi {@link OpremaRepository} za pristup podacima
 * i {@link OpremaMapper} za mapiranje između entiteta i DTO objekata.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.OpremaMapper
 */
@AllArgsConstructor
@Service
public class OpremaService {

	private final OpremaRepository opremaRepository;
	private final OpremaMapper opremaMapper;
	
	/**
     * Vraća listu sve dostupne opreme u sistemu.
     *
     * @return lista {@link OpremaDto} objekata
     */
	public List<OpremaDto> findAll() {
		return opremaRepository.findAll().stream().map(opremaMapper::toDto).toList();
	}

	/**
     * Pronalazi opremu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator opreme
     * @return {@link OpremaDto} ako oprema postoji,
     *         ili {@code null} ako oprema ne postoji
     */
	public OpremaDto findById(Long id) {
		return opremaRepository.findById(id).map(opremaMapper::toDto).orElse(null);
	}

	
}
