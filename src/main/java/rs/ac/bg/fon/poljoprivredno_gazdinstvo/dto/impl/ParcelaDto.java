package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prenos podataka o parceli.
 * 
 * Ovaj DTO se koristi za kreiranje, izmenu i prikaz parcela
 * u okviru sistema poljoprivrednog gazdinstva. Sadrži osnovne
 * podatke o parceli i referencu na tip zemljišta.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParcelaDto {

	/**
     * Jedinstveni identifikator parcele.
     */
	private Long parcelaID;

	/**
     * Naziv parcele.
     * 
     * Polje je obavezno i može imati najviše 50 karaktera.
     * 
     */
	@NotBlank(message = "Naziv parcele je obavezan")
	@Size(max=50, message = "Naziv parcele moze imati najvise 50 karaktera")
	private String naziv;

	/**
     * Lokacija parcele.
     * 
     * Polje je obavezno i može imati najviše 50 karaktera.
     * 
     */
	@NotBlank(message = "Lokacija parcele je obavezna")
	@Size(max=50, message = "Lokacija moze imati najvise 50 karaktera")
	private String lokacija;

	/**
     * Površina parcele.
     * 
     * Vrednost mora biti pozitivna i izražena u odgovarajućim
     * jedinicama površine (npr. hektari).
     * 
     */
	@NotNull(message = "Povrsina parcele je obavezna")
	@Positive(message = "Povrsina mora biti veca od nula")
	private Double povrsina;
	
	 /**
     * Identifikator tipa zemljišta kome parcela pripada.
     * 
     * Polje je obavezno i predstavlja referencu na tip zemljišta.
     * 
     */
	@NotNull(message = "Tip zemljista je obavezan")
	private Long tipZemljista;
	
	 /**
     * Kreira DTO parcele sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi kada je potrebno referencirati parcelu
     * samo putem njenog identifikatora.
     * 
     *
     * @param parcelaID jedinstveni identifikator parcele
     */
	public ParcelaDto(Long parcelaID) {
		super();
		this.parcelaID = parcelaID;
	}

	
	
}
