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

/**
 * Servisni sloj za upravljanje poljoprivrednim kulturama.
 * <p>
 * Ova klasa sadrži poslovnu logiku vezanu za:
 * <ul>
 *   <li>upravljanje kulturama</li>
 *   <li>kreiranje, izmenu i brisanje kultura</li>
 *   <li>mapiranje između entiteta i DTO objekata</li>
 * </ul>
 * </p>
 *
 * <p>
 * Servis koristi {@link KulturaRepository} za pristup bazi podataka
 * i {@link KulturaMapper} za mapiranje podataka.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.KulturaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.KulturaMapper
 */
@Service
public class KulturaService {

	private final KulturaRepository kulturaRepository;
	private final KulturaMapper kulturaMapper;

	 /**
     * Kreira novi {@code KulturaService} sa prosleđenim zavisnostima.
     *
     * @param kulturaRepository repozitorijum za upravljanje kulturama
     * @param kulturaMapper     mapper za konverziju između entiteta i DTO objekata
     */
	@Autowired
	public KulturaService(KulturaRepository kulturaRepository, KulturaMapper kulturaMapper) {
		this.kulturaRepository = kulturaRepository;
		this.kulturaMapper = kulturaMapper;
	}

	/**
     * Vraća listu svih poljoprivrednih kultura u sistemu.
     *
     * @return lista {@link KulturaDto} objekata
     */
	public List<KulturaDto> findAll() {
		return kulturaRepository.findAll().stream().map(kulturaMapper::toDto).toList();
	}

	/**
     * Pronalazi kulturu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator kulture
     * @return {@link KulturaDto} ako kultura postoji,
     *         ili {@code null} ako ne postoji
     */
	public KulturaDto findById(Long id) {
		return kulturaRepository.findById(id).map(kulturaMapper::toDto).orElse(null);
	}

	/**
     * Kreira novu poljoprivrednu kulturu.
     *
     * @param dto DTO objekat sa podacima o kulturi
     * @return {@link KulturaDto} kreirane kulture
     */
	public KulturaDto save( KulturaDto dto) {
		Kultura kultura = kulturaMapper.toEntity(dto);
		Kultura saved = kulturaRepository.save(kultura);

		return kulturaMapper.toDto(saved);
	}

	 /**
     * Ažurira postojeću poljoprivrednu kulturu.
     *
     * @param id  jedinstveni identifikator kulture
     * @param dto DTO objekat sa izmenjenim podacima o kulturi
     * @return {@link KulturaDto} ažurirana kultura,
     *         ili {@code null} ako kultura ne postoji
     */
	public KulturaDto update(Long id,  KulturaDto dto) {
		return kulturaRepository.findById(id).map(existing -> {
			existing.setNaziv(dto.getNaziv());
			existing.setSorta(dto.getSorta());

			Kultura saved = kulturaRepository.save(existing);
			return kulturaMapper.toDto(saved);
		}).orElse(null);
	}
	
	 /**
     * Briše poljoprivrednu kulturu iz sistema.
     *
     * @param id jedinstveni identifikator kulture
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako kultura sa zadatim ID-jem ne postoji
     */
	public void delete(Long id) {
		var kultura=kulturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
	            "Parcela sa id=" + id + " ne postoji"
	        ));
		
		kulturaRepository.delete(kultura);
		
	}
	
}
