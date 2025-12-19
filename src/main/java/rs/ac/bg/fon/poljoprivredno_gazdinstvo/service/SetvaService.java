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

/**
 * Servisni sloj za upravljanje setvama u sistemu.
 * <p>
 * Setva predstavlja centralni poslovni entitet koji povezuje:
 * administratora, parcelu, kulturu i listu stavki setve (aktivnosti sa datumom i cenom).
 * </p>
 *
 * <p>
 * Ova klasa sadrži poslovnu logiku vezanu za:
 * <ul>
 *   <li>upravljanje setvama (CRUD)</li>
 *   <li>validaciju postojanja povezanih entiteta (administrator, parcela, kultura, aktivnost)</li>
 *   <li>kreiranje i ažuriranje stavki setve uz održavanje veza parent-child</li>
 * </ul>
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.SetvaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.SetvaMapper
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve
 */
@AllArgsConstructor
@Service
public class SetvaService {

	private final SetvaRepository setvaRepository;
	private final SetvaMapper setvaMapper;
	private final AdministratorRepository administratorRepository;
	private final ParcelaRepository parcelaRepository;
	private final KulturaRepository kulturaRepository;
	private final AktivnostRepository aktivnostRepository;
	
	/**
     * Vraća listu svih setvi u sistemu.
     *
     * @return lista {@link SetvaDto} objekata
     */
	public List<SetvaDto> findAll() {
		return setvaRepository.findAll().stream().map(setvaMapper::toDto).toList();
	}

	/**
     * Pronalazi setvu na osnovu njenog identifikatora.
     *
     * @param id jedinstveni identifikator setve
     * @return {@link SetvaDto} ako setva postoji,
     *         ili {@code null} ako ne postoji
     */
	public SetvaDto findById(Long id) {
		return setvaRepository.findById(id).map(setvaMapper::toDto).orElse(null);
	}

	/**
     * Kreira novu setvu u sistemu.
     * <p>
     * Metoda validira postojanje administratora, parcele i kulture,
     * mapira DTO u entitet i povezuje setvu sa stavkama setve.
     * </p>
     *
     * @param setvaDto DTO objekat sa podacima o setvi i njenim stavkama
     * @return {@link SetvaDto} kreirane setve
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako administrator, parcela ili kultura ne postoje
     */
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

	 /**
     * Ažurira postojeću setvu.
     * <p>
     * Metoda ažurira osnovna polja setve (datumi, status i veze sa entitetima),
     * a zatim ponovo formira listu stavki setve na osnovu prosleđenog DTO-a.
     * </p>
     *
     * <p>
     * Tokom ažuriranja:
     * <ul>
     *   <li>proverava se postojanje setve</li>
     *   <li>proverava se postojanje administratora, parcele i kulture</li>
     *   <li>postojeće stavke se brišu iz kolekcije i formira se nova lista</li>
     *   <li>za svaku stavku se validira postojanje aktivnosti</li>
     * </ul>
     * </p>
     *
     * @param setvaDto DTO objekat sa izmenjenim podacima o setvi i stavkama
     * @param id       jedinstveni identifikator setve
     * @return {@link SetvaDto} ažurirana setva
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako setva, administrator, parcela, kultura ili aktivnost ne postoje
     */
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

	/**
     * Briše setvu iz sistema.
     *
     * @param id jedinstveni identifikator setve
     *
     * @throws jakarta.persistence.EntityNotFoundException
     *         ako setva sa zadatim ID-jem ne postoji
     */
	public void delete(Long id) {
		
		var setva = setvaRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Setva sa id=" + id + " ne postoji"
		        ));

		    setvaRepository.delete(setva);
	}
	
	

}
