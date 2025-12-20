package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.KulturaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

/**
 * Mapper za konverziju izmedju {@link Kultura} entiteta i {@link KulturaDto}.
 * 
 * Ova klasa implementira genericki {@link DtoEntityMapper} interfejs
 * i obezbedjuje mapiranje osnovnih atributa poljoprivredne kulture
 * izmedju domenskog i DTO sloja.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper
 */
@Component
public class KulturaMapper implements DtoEntityMapper<KulturaDto, Kultura>{

	 /**
     * {@inheritDoc}
     */
	@Override
	public KulturaDto toDto(Kultura e) {
		
		if(e==null)
			return null;
		
		return new KulturaDto(e.getKulturaID(), e.getNaziv(), e.getSorta());
	}

	/**
     * {@inheritDoc}
     */
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
