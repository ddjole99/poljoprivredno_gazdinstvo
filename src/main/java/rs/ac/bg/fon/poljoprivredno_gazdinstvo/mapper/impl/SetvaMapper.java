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

/**
 * Mapper za konverziju izmedju {@link Setva} entiteta i {@link SetvaDto}.
 * 
 * Ova klasa mapira slozeni agregat {@code Setva}, ukljucujuci:
 * <ul>
 *   <li>osnovne podatke o setvi (datumi, status)</li>
 *   <li>reference na administratora, parcelu i kulturu (putem ID-jeva)</li>
 *   <li>listu stavki setve koje predstavljaju izvrsene aktivnosti</li>
 * </ul>
 * 
 * 
 * Mapper je registrovan kao Spring bean pomocu anotacije {@link Component}
 * i koristi se u servisnom sloju aplikacije.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.DtoEntityMapper
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve
 */
@Component
public class SetvaMapper implements DtoEntityMapper<SetvaDto, Setva> {

	/**
     * {@inheritDoc}
     *
     * 
     * Prilikom mapiranja:
     * <ul>
     *   <li>reference na administratora, parcelu i kulturu mapiraju se
     *       u DTO kao njihovi identifikatori</li>
     *   <li>svaka {@link StavkaSetve} se mapira u {@link StavkaSetveDto}</li>
     *   <li>ako neka referenca nije postavljena, odgovarajući ID u DTO-u
     *       će biti {@code null}</li>
     * </ul>
     * 
     */
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

	/**
     * {@inheritDoc}
     *
     * 
     * Prilikom mapiranja iz DTO-a u entitet:
     * <ul>
     *   <li>kreira se nova instanca {@link Setva}</li>
     *   <li>svaka {@link StavkaSetveDto} se mapira u {@link StavkaSetve}</li>
     *   <li>svaka stavka se povezuje sa roditeljskom setvom</li>
     *   <li>aktivnost se mapira korišćenjem njenog identifikatora</li>
     * </ul>
     * 
     *
     * 
     * Reference na administratora, parcelu i kulturu se ne postavljaju
     * direktno u ovoj metodi, vec se obicno dodaju u servisnom sloju.
     * 
     */
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
