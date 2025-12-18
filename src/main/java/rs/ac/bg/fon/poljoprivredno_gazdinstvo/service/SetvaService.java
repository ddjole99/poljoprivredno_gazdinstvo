package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.StavkaSetveDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.SetvaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AdministratorRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AktivnostRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.KulturaRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.ParcelaRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.SetvaRepository;

@AllArgsConstructor
@Service
public class SetvaService {

	private final SetvaRepository setvaRepository;
	private final SetvaMapper setvaMapper;
	private final AdministratorRepository administratorRepository;
	private final ParcelaRepository parcelaRepository;
	private final KulturaRepository kulturaRepository;
	private final AktivnostRepository aktivnostRepository;
	
	public List<SetvaDto> findAll() {
		return setvaRepository.findAll().stream().map(setvaMapper::toDto).toList();
	}

	public SetvaDto findById(Long id) {
		return setvaRepository.findById(id).map(setvaMapper::toDto).orElse(null);
	}

	public SetvaDto save(SetvaDto setvaDto) {
		var admin = administratorRepository.findById(setvaDto.getAdministratorID())
				.orElseThrow(() -> new EntityNotFoundException(
						"Administrator sa id=" + setvaDto.getAdministratorID() + " ne postoji"));

		var parcela = parcelaRepository.findById(setvaDto.getParcelaID()).orElseThrow(
				() -> new EntityNotFoundException("Parcela sa id=" + setvaDto.getParcelaID() + " ne postoji"));

		var kultura = kulturaRepository.findById(setvaDto.getKulturaID()).orElseThrow(
				() -> new EntityNotFoundException("Kultura sa id=" + setvaDto.getKulturaID() + " ne postoji"));

		var setva = setvaMapper.toEntity(setvaDto);
		
		setva.setAdministrator(admin);
		setva.setParcela(parcela);
		setva.setKultura(kultura);

		setva.getStavkeSetve().forEach(stavka -> stavka.setSetva(setva));

		var saved = setvaRepository.save(setva);
		return setvaMapper.toDto(saved);
	}

	public SetvaDto update(@Valid SetvaDto setvaDto, Long id) {
		var existing = setvaRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Setva sa ID " + id + " nije pronađena."));

		    existing.setDatumPocetka(setvaDto.getDatumPocetka());
		    existing.setDatumZavrsetka(setvaDto.getDatumZavrsetka());
		    existing.setStatus(setvaDto.getStatus());

		    var admin = administratorRepository.findById(setvaDto.getAdministratorID())
		        .orElseThrow(() -> new EntityNotFoundException("Administrator ne postoji"));
		    var parcela = parcelaRepository.findById(setvaDto.getParcelaID())
		        .orElseThrow(() -> new EntityNotFoundException("Parcela ne postoji"));
		    var kultura = kulturaRepository.findById(setvaDto.getKulturaID())
		        .orElseThrow(() -> new EntityNotFoundException("Kultura ne postoji"));

		    existing.setAdministrator(admin);
		    existing.setParcela(parcela);
		    existing.setKultura(kultura);

		    existing.getStavkeSetve().clear();
		    if (setvaDto.getStavkeSetve() != null) {
		        for (StavkaSetveDto sDto : setvaDto.getStavkeSetve()) {
		            StavkaSetve st = new StavkaSetve();
		            st.setId(sDto.getId());
		            st.setDatum(sDto.getDatum());
		            st.setCena(sDto.getCena());

		            Aktivnost aktivnost = aktivnostRepository.findById(sDto.getAktivnostID())
		                .orElseThrow(() -> new EntityNotFoundException("Aktivnost ne postoji"));
		            st.setAktivnost(aktivnost);

		            st.setSetva(existing);         
		            existing.getStavkeSetve().add(st);
		        }
		    }

		    var saved = setvaRepository.save(existing);
		    return setvaMapper.toDto(saved);
	}

	public void delete(Long id) {
		
		var setva = setvaRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Setva sa id=" + id + " ne postoji"
		        ));

		    setvaRepository.delete(setva);
	}
	
	

}
