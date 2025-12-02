package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.OpremaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

@Component
public class OpremaMapper implements DtoEntityMapper<OpremaDto, Oprema>{

	@Override
	public OpremaDto toDto(Oprema e) {
		if(e==null)
			return null;
		return new OpremaDto(e.getOpremaID(), e.getNaziv(), e.getTipOpreme());
	}

	@Override
	public Oprema toEntity(OpremaDto t) {
		if(t==null)
			return null;
		Oprema oprema=new Oprema();
		oprema.setNaziv(t.getNaziv());
		oprema.setTipOpreme(t.getTipOpreme());
		
		return oprema;
	}

}
