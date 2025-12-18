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

@AllArgsConstructor
@Service
public class ParcelaService {

	private final ParcelaMapper parcelaMapper;
	private final ParcelaRepository parcelaRepository;
	private final TipZemljistaRepository tipZemljistaRepository;
	
	
	public List<ParcelaDto> findAll() {
		return parcelaRepository.findAll().stream().map(parcelaMapper::toDto).toList();
	}



	public ParcelaDto findById(Long id) {
		return parcelaRepository.findById(id).map(parcelaMapper::toDto).orElse(null);		
	}



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



	public void delete(Long id) {
		var parcela = parcelaRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Parcela sa id=" + id + " ne postoji"
		        ));

		    parcelaRepository.delete(parcela);
	}

}
