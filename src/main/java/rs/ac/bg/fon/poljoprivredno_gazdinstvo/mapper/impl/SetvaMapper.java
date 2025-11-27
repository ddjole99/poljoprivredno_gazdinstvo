package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.StavkaSetveDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

public class SetvaMapper implements DtoEntityMapper<SetvaDto, Setva> {

	@Override
	public SetvaDto toDto(Setva e) {
		if (e == null)
			return null;

		SetvaDto dto = new SetvaDto();
		dto.setSetvaID(e.getSetvaID());
		dto.setAdministratorID(e.getAdministrator() != null ? e.getAdministrator().getAdministratorID() : null);
		dto.setAdministratorID(e.getParcela() != null ? e.getParcela().getParcelaID() : null);
		dto.setAdministratorID(e.getKultura() != null ? e.getKultura().getKulturaID() : null);
		dto.setDatumPocetka(e.getDatumPocetka());
		dto.setDatumZavrsetka(e.getDatumZavrsetka());
		dto.setStatus(e.getStatus());
		dto.setStavkeSetve(e.getStavkeSetve().stream()
				.map(s -> new StavkaSetveDto(s.getId(), s.getDatum(), s.getAktivnost().getAktivnostID(), s.getCena()))
				.toList());
		
		return dto;
	}

	@Override
	public Setva toEntity(SetvaDto t) {
		if (t == null)
			return null;

		Setva s = new Setva();
		s.setSetvaID(t.getSetvaID());
		s.setDatumPocetka(t.getDatumPocetka());
		s.setDatumZavrsetka(t.getDatumZavrsetka());
		s.setStatus(t.getStatus());

		s.setParcela(new Parcela(t.getParcelaID()));
		s.setKultura(new Kultura(t.getKulturaID()));

		s.setStavkeSetve(t.getStavkeSetve().stream().map(st->{
			StavkaSetve stavka=new StavkaSetve();
			stavka.setId(st.getId());
			stavka.setCena(st.getCena());
			stavka.setDatum(st.getDatum());
			stavka.setAktivnost(new Aktivnost(st.getAktivnostID()));
			stavka.setSetva(s);
			return stavka;
		}).toList());
		
		return s;
	}

}
