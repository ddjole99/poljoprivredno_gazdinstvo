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

@Service
@AllArgsConstructor
public class AktivnostService {

	AktivnostRepository aktivnostRepository;
	AktivnostMapper aktivnostMapper;
	OpremaRepository opremaRepository;
	
	public List<AktivnostDto> findAll() {
		return aktivnostRepository.findAll().stream().map(aktivnostMapper::toDto).toList();
	}


	public AktivnostDto findById(Long id) {
		return aktivnostRepository.findById(id).map(aktivnostMapper::toDto).orElse(null);
	}


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


	public void delete(Long id) {
		var aktivnost = aktivnostRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aktivnost sa id=" + id + " ne postoji"
		        ));

		    aktivnostRepository.delete(aktivnost);
		
		
		
	}
	
	

}
