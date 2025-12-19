package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AktivnostDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipAktivnosti;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

/**
 * Mapper za konverziju između {@link Aktivnost} entiteta i {@link AktivnostDto}.
 * <p>
 * Ova implementacija mapira osnovna polja aktivnosti, kao i listu
 * povezane opreme, koja se u DTO sloju predstavlja kao lista
 * identifikatora opreme.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper
 */
@Component
public class AktivnostMapper implements DtoEntityMapper<AktivnostDto, Aktivnost> {

	/**
     * {@inheritDoc}
     *
     * <p>
     * Lista opreme se mapira u listu identifikatora opreme.
     * Tip aktivnosti se mapira u {@code String} korišćenjem
     * {@link Enum#name()} metode.
     * </p>
     */
	@Override
	public AktivnostDto toDto(Aktivnost e) {
		if (e == null) {
			return null;
		}
		List<Long> opremaIds = e.getOprema().stream().map(Oprema::getOpremaID).toList();
		return new AktivnostDto(e.getAktivnostID(), e.getNaziv(), e.getTipAktivnosti().name(), opremaIds);
	}

	/**
     * {@inheritDoc}
     *
     * <p>
     * Tip aktivnosti se mapira iz {@code String} vrednosti DTO-a
     * u {@link TipAktivnosti} enum korišćenjem metode
     * {@link TipAktivnosti#valueOf(String)}.
     * </p>
     *
     * @throws java.lang.IllegalArgumentException
     *         ako prosleđeni naziv tipa aktivnosti ne odgovara
     *         nijednoj vrednosti {@link TipAktivnosti} enuma
     */
	@Override
	public Aktivnost toEntity(AktivnostDto t) {
		if (t == null)
			return null;
		Aktivnost aktivnost = new Aktivnost();
		aktivnost.setAktivnostID(t.getAktivnostID());
		aktivnost.setNaziv(t.getNaziv());
		aktivnost.setTipAktivnosti(TipAktivnosti.valueOf(t.getTipAktivnosti()));
		
		return aktivnost;

	}

}
