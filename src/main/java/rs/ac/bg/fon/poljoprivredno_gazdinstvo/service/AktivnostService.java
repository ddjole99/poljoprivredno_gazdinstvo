package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AktivnostDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipAktivnosti;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.AktivnostMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AktivnostRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository;

/**
 * Servisni sloj za upravljanje aktivnostima u sistemu.
 * 
 * Ova klasa sadrzi poslovnu logiku vezanu za:
 * <ul>
 *   <li>upravljanje aktivnostima</li>
 *   <li>dodelu opreme aktivnostima</li>
 *   <li>validaciju postojanja povezane opreme</li>
 * </ul>
 *
 *
 * Servis koristi {@link AktivnostRepository} za pristup bazi podataka
 * i {@link AktivnostMapper} za mapiranje izmedju entiteta i DTO objekata.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AktivnostRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.AktivnostMapper
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository
 */
@Service
@AllArgsConstructor
public class AktivnostService {

	AktivnostRepository aktivnostRepository;
	AktivnostMapper aktivnostMapper;
	OpremaRepository opremaRepository;
	
	/**
     * Vraca listu svih aktivnosti u sistemu.
     *
     * @return lista {@link AktivnostDto} objekata
     */
	public List<AktivnostDto> findAll() {
		return aktivnostRepository.findAll().stream().map(aktivnostMapper::toDto).toList();
	}


	/**
     * Pronalazi aktivnost na osnovu identifikatora.
     *
     * @param id jedinstveni identifikator aktivnosti
     * @return {@link AktivnostDto} ako aktivnost postoji,
     *         ili {@code null} ako ne postoji
     */
	public AktivnostDto findById(Long id) {
		return aktivnostRepository.findById(id).map(aktivnostMapper::toDto).orElse(null);
	}


	 /**
     * Kreira novu aktivnost u sistemu.
     * 
     * Metoda proverava da li sva oprema prosleđena u zahtevu
     * postoji u sistemu pre nego sto sacuva aktivnost.
     *
     * @param dto DTO objekat sa podacima o aktivnosti
     * @return {@link AktivnostDto} kreirane aktivnosti
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako jedna ili vise stavki opreme ne postoje
     */
	public AktivnostDto save(AktivnostDto dto) {
		var aktivnost = aktivnostMapper.toEntity(dto);

	    
	    List<Oprema> oprema = opremaRepository.findAllById(dto.getOpremaIDs());

	    if (oprema.size() != dto.getOpremaIDs().size()) {
	        throw new EntityNotFoundException("Jedna ili vise stavki opreme ne postoje");
	    }

	    aktivnost.setOprema(oprema);

	    var saved = aktivnostRepository.save(aktivnost);

	    return aktivnostMapper.toDto(saved);
	}


	/**
     * Azurira postojecu aktivnost.
     * 
     * Metoda omogucava izmenu osnovnih podataka aktivnosti
     * i azuriranje liste dodeljene opreme.
     * 
     *
     * @param id  jedinstveni identifikator aktivnosti
     * @param dto DTO objekat sa izmenjenim podacima o aktivnosti
     * @return {@link AktivnostDto} azurirana aktivnost
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako aktivnost ili neka od stavki opreme ne postoji
     * @throws java.lang.IllegalArgumentException
     *         ako je prosleđena nevalidna vrednost za {@link TipAktivnosti}
     */
	public AktivnostDto update(Long id, AktivnostDto dto) {
		var existing = aktivnostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aktivnost sa id=" + id + " ne postoji"));

        existing.setNaziv(dto.getNaziv());
        existing.setTipAktivnosti(TipAktivnosti.valueOf(dto.getTipAktivnosti())
        );

        List<Long> ids = dto.getOpremaIDs();
        if (ids != null) {
            List<Oprema> oprema = opremaRepository.findAllById(ids);
            if (oprema.size() != ids.size())
                throw new EntityNotFoundException("Jedna ili vise oprema ne postoji");

            existing.setOprema(oprema);
        } 
        
        var saved = aktivnostRepository.save(existing);
        return aktivnostMapper.toDto(saved);
	}


	/**
     * Brise aktivnost iz sistema.
     *
     * @param id jedinstveni identifikator aktivnosti
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako aktivnost sa zadatim ID-jem ne postoji
     */
	public void delete(Long id) {
		var aktivnost = aktivnostRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aktivnost sa id=" + id + " ne postoji"
		        ));

		    aktivnostRepository.delete(aktivnost);
		
		
		
	}
	
	

}
