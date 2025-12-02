package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper;

@Component
public class ParcelaMapper implements DtoEntityMapper<ParcelaDto, Parcela> {

	@Override
	public ParcelaDto toDto(Parcela e) {

		if (e == null)
			return null;

		return new ParcelaDto(e.getParcelaID(), e.getNaziv(), e.getLokacija(), e.getPovrsina(),
				e.getTipZemljista().getTipZemljistaID());
	}

	@Override
	public Parcela toEntity(ParcelaDto t) {

		if(t==null)
			return null;
		
		Parcela parcela=new Parcela();
		parcela.setParcelaID(t.getParcelaID());
		parcela.setNaziv(t.getNaziv());
		parcela.setLokacija(t.getLokacija());
		parcela.setPovrsina(t.getPovrsina());
		if(t.getTipZeljista()!=null)
			parcela.setTipZemljista(new TipZemljista(t.getTipZeljista()));
		
		return parcela;
	}

}
