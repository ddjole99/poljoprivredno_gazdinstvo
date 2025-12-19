package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

/**
 * Mapper za konverziju između {@link Parcela} entiteta i {@link ParcelaDto}.
 * <p>
 * Ova klasa implementira generički {@link DtoEntityMapper} interfejs
 * i obezbeđuje mapiranje osnovnih podataka o parceli između
 * domenskog i DTO sloja. Tip zemljišta se u DTO sloju predstavlja
 * putem identifikatora.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
 */
@Component
public class ParcelaMapper implements DtoEntityMapper<ParcelaDto, Parcela> {

	/**
     * {@inheritDoc}
     *
     * <p>
     * Tip zemljišta se mapira u DTO kao identifikator
     * {@link TipZemljista#getTipZemljistaID()}.
     * </p>
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
     * <p>
     * Tip zemljišta se mapira iz identifikatora DTO-a u entitet
     * korišćenjem pomoćnog konstruktora {@link TipZemljista#TipZemljista(Long)}.
     * </p>
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
