package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.KulturaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

public class KulturaMapper implements DtoEntityMapper<KulturaDto, Kultura>{

	@Override
	public KulturaDto toDto(Kultura e) {
		
		if(e==null)
			return null;
		
		return new KulturaDto(e.getKulturaID(), e.getNaziv(), e.getSorta());
	}

	@Override
	public Kultura toEntity(KulturaDto t) {
		
		if(t==null)
			return null;
		
		Kultura kultura=new Kultura();
		kultura.setKulturaID(t.getKulturaID());
		kultura.setNaziv(t.getNaziv());
		kultura.setSorta(t.getSorta());
		return kultura;
	}
	
}
