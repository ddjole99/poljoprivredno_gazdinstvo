package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.ParcelaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.ParcelaRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.TipZemljistaRepository;

/**
 * Servisni sloj za upravljanje parcelama.
 *
 * Ova klasa sadrzi poslovnu logiku vezanu za:
 * <ul>
 *   <li>upravljanje parcelama</li>
 *   <li>povezivanje parcele sa tipom zemljišta</li>
 *   <li>validaciju postojanja povezanih entiteta</li>
 * </ul>
 * 
 *
 * Servis koristi {@link ParcelaRepository} za pristup bazi podataka,
 * {@link TipZemljistaRepository} za validaciju tipa zemljišta i
 * {@link ParcelaMapper} za mapiranje između entiteta i DTO objekata.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.ParcelaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.TipZemljistaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.ParcelaMapper
 */
@AllArgsConstructor
@Service
public class ParcelaService {

	private final ParcelaMapper parcelaMapper;
	private final ParcelaRepository parcelaRepository;
	private final TipZemljistaRepository tipZemljistaRepository;
	
	/**
     * Vraća listu svih parcela u sistemu.
     *
     * @return lista {@link ParcelaDto} objekata
     */
	public List<ParcelaDto> findAll() {
		return parcelaRepository.findAll().stream().map(parcelaMapper::toDto).toList();
	}


	 /**
     * Pronalazi parcelu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator parcele
     * @return {@link ParcelaDto} ako parcela postoji,
     *         ili {@code null} ako ne postoji
     */
	public ParcelaDto findById(Long id) {
		return parcelaRepository.findById(id).map(parcelaMapper::toDto).orElse(null);		
	}


	/**
     * Kreira novu parcelu u sistemu.
     * 
     * Metoda proverava da li prosledjeni tip zemljista postoji
     * pre nego sto sacuva parcelu.
     * 
     *
     * @param dto DTO objekat sa podacima o parceli
     * @return {@link ParcelaDto} kreirane parcele
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako tip zemljista ne postoji
     */
	public ParcelaDto save(ParcelaDto dto) {
		
		TipZemljista tz = tipZemljistaRepository.findById(dto.getTipZemljista())
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Tip zemljišta sa id=" + dto.getTipZemljista() + " ne postoji"
		        ));
		
		var parcela=parcelaMapper.toEntity(dto);
		
		parcela.setTipZemljista(tz);
		var saved=parcelaRepository.save(parcela);
		
		
		return parcelaMapper.toDto(saved);
		
	}


	/**
     * Azurira postojecu parcelu.
     *
     * @param dto DTO objekat sa izmenjenim podacima o parceli
     * @param id  jedinstveni identifikator parcele
     * @return {@link ParcelaDto} azuriranu parcelu
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako parcela ili tip zemljišta ne postoje
     */
	public ParcelaDto update(ParcelaDto dto, Long id) {
		var parcela = parcelaRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Parcela sa id=" + id + " ne postoji"));

		    TipZemljista tz = tipZemljistaRepository.findById(dto.getTipZemljista())
		        .orElseThrow(() -> new EntityNotFoundException("Tip zemljišta sa id=" + dto.getTipZemljista() + " ne postoji"));

		    parcela.setNaziv(dto.getNaziv());
		    parcela.setLokacija(dto.getLokacija());
		    parcela.setPovrsina(dto.getPovrsina());
		    parcela.setTipZemljista(tz);

		    
		     parcelaRepository.save(parcela);

		    return parcelaMapper.toDto(parcela);
	}


	 /**
     * Brise parcelu iz sistema.
     *
     * @param id jedinstveni identifikator parcele
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako parcela sa zadatim ID-jem ne postoji
     */
	public void delete(Long id) {
		var parcela = parcelaRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Parcela sa id=" + id + " ne postoji"
		        ));

		    parcelaRepository.delete(parcela);
	}

}
