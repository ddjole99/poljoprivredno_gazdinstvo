package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.StavkaSetveDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

@Component
public class SetvaMapper implements DtoEntityMapper<SetvaDto, Setva> {

	@Override
	public SetvaDto toDto(Setva e) {
		if (e == null) return null;

	    SetvaDto dto = new SetvaDto();
	    dto.setSetvaID(e.getSetvaID());
	    dto.setAdministratorID(
	        e.getAdministrator() != null ? e.getAdministrator().getAdministratorID() : null
	    );
	    dto.setParcelaID(
	        e.getParcela() != null ? e.getParcela().getParcelaID() : null
	    );
	    dto.setKulturaID(
	        e.getKultura() != null ? e.getKultura().getKulturaID() : null
	    );
	    dto.setDatumPocetka(e.getDatumPocetka());
	    dto.setDatumZavrsetka(e.getDatumZavrsetka());
	    dto.setStatus(e.getStatus());

	    dto.setStavkeSetve(
	        e.getStavkeSetve().stream()
	            .map(s -> new StavkaSetveDto(
	                s.getId(),
	                s.getDatum(),
	                s.getAktivnost().getAktivnostID(),
	                s.getCena()
	            ))
	            .toList()
	    );

	    return dto;
	}

	@Override
	public Setva toEntity(SetvaDto dto) {
		if (dto == null) return null;

        Setva setva = new Setva();
        setva.setSetvaID(dto.getSetvaID());
        setva.setDatumPocetka(dto.getDatumPocetka());
        setva.setDatumZavrsetka(dto.getDatumZavrsetka());
        setva.setStatus(dto.getStatus());

        if (dto.getStavkeSetve() != null) {
            for (StavkaSetveDto sDto : dto.getStavkeSetve()) {
                StavkaSetve st = new StavkaSetve();

                st.setId(sDto.getId());
                st.setDatum(sDto.getDatum());
                st.setCena(sDto.getCena());

                if (sDto.getAktivnostID() != null) {
                    st.setAktivnost(new Aktivnost(sDto.getAktivnostID()));
                }

                st.setSetva(setva);
                setva.getStavkeSetve().add(st);
            }
        }

        return setva;
	}

}
