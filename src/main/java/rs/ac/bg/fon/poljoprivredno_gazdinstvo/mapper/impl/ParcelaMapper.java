package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

/**
 * Mapper za konverziju izmedju {@link Parcela} entiteta i {@link ParcelaDto}.
 * 
 * Ova klasa implementira genericki {@link DtoEntityMapper} interfejs
 * i obezbeđuje mapiranje osnovnih podataka o parceli izmedju
 * domenskog i DTO sloja. Tip zemljista se u DTO sloju predstavlja
 * putem identifikatora.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
 */
@Component
public class ParcelaMapper implements DtoEntityMapper<ParcelaDto, Parcela> {

	/**
     * {@inheritDoc}
     *
     * 
     * Tip zemljista se mapira u DTO kao identifikator
     * {@link TipZemljista#getTipZemljistaID()}.
     * 
     */
	@Override
	public ParcelaDto toDto(Parcela e) {

		if (e == null)
			return null;

		return new ParcelaDto(e.getParcelaID(), e.getNaziv(), e.getLokacija(), e.getPovrsina(),
				e.getTipZemljista().getTipZemljistaID());
	}

	 /**
     * {@inheritDoc}
     *
     * 
     * Tip zemljista se mapira iz identifikatora DTO-a u entitet
     * koriscenjem pomocnog konstruktora {@link TipZemljista#TipZemljista(Long)}.
     * 
     */
	@Override
	public Parcela toEntity(ParcelaDto t) {

		if(t==null)
			return null;
		
		Parcela parcela=new Parcela();
		parcela.setParcelaID(t.getParcelaID());
		parcela.setNaziv(t.getNaziv());
		parcela.setLokacija(t.getLokacija());
		parcela.setPovrsina(t.getPovrsina());
		if(t.getTipZemljista()!=null)
			parcela.setTipZemljista(new TipZemljista(t.getTipZemljista()));
		
		return parcela;
	}

}
