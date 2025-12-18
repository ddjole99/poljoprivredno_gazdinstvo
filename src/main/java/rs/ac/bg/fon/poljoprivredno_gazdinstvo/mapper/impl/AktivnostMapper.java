package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AktivnostDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipAktivnosti;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

@Component
public class AktivnostMapper implements DtoEntityMapper<AktivnostDto, Aktivnost> {

	@Override
	public AktivnostDto toDto(Aktivnost e) {
		if (e == null) {
			return null;
		}
		List<Long> opremaIds = e.getOprema().stream().map(Oprema::getOpremaID).toList();
		return new AktivnostDto(e.getAktivnostID(), e.getNaziv(), e.getTipAktivnosti().name(), opremaIds);
	}

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
