package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Status;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve;

/**
 * DTO za prenos podataka o setvi.
 * 
 * Ovaj DTO se koristi za kreiranje, izmenu i prikaz setve u okviru
 * sistema poljoprivrednog gazdinstva. Sadrži osnovne informacije o setvi
 * (administrator, parcela, kultura, period trajanja, status) kao i listu
 * stavki setve koje detaljno opisuju izvršene aktivnosti.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.StavkaSetveDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SetvaDto {

	/**
     * Jedinstveni identifikator setve.
     */
	private Long setvaID;

	 /**
     * Identifikator administratora odgovornog za setvu
     * koji ne sme biti null.
     */
	@NotNull(message = "Administrator je obavezan")
	private Long administratorID;

	/**
     * Identifikator kulture koja se seje koji ne sme biti
     * null.
     */
	@NotNull(message = "Kultura je obavezna")
	private Long kulturaID;

	/**
     * Identifikator parcele na kojoj se setva izvršava koji ne sme
     * biti null.
     */
	@NotNull(message = "Parcela je obavezna")
	private Long parcelaID;

	/**
     * Datum pocetka setve koji ne sme biti null.
     */
	@NotNull(message = "Datum pocetka je obavezan")
	private LocalDate datumPocetka;

	 /**
     * Datum zavrsetka setve koji ne sme biti null.
     */
	@NotNull(message = "Datum zavrsetka je obavezan")
	private LocalDate datumZavrsetka;
	
	/**
     * Trenutni status setve koji ne sme biti null.
     *
     * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Status
     */
	@NotNull(message = "Status setve je obavezan")
	private Status status;
	
	/**
     * Lista stavki setve.
     * 
     * Svaka stavka predstavlja jednu konkretnu aktivnost izvrsenu
     * u okviru setve. Validacija se primenjuje i na svaku stavku
     * pojedinacno.
     * 
     */
	@Valid
	private List<StavkaSetveDto> stavkeSetve=new ArrayList<StavkaSetveDto>();
	
	 /**
     * Kreira DTO setve sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi kada je potrebno referencirati setvu
     * samo putem njenog identifikatora.
     * 
     *
     * @param setvaID jedinstveni identifikator setve
     */
	public SetvaDto(Long setvaID) {
		this.setvaID = setvaID;
	}

}
