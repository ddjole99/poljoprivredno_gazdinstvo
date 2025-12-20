package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prenos podataka o poljoprivrednoj kulturi.
 * 
 * Ovaj DTO se koristi za kreiranje, izmenu i prikaz podataka o kulturi
 * u okviru sistema poljoprivrednog gazdinstva.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KulturaDto {
	
	/**
     * Jedinstveni identifikator kulture.
     */
	private Long kulturaID;
	
	/**
     * Naziv kulture.
     * 
     * Polje je obavezno i može imati najviše 50 karaktera.
     * 
     */
	@NotBlank(message = "Naziv kulture je obavezan")
	@Size(max=50, message = "Naziv kulture moze imati najvise 50 karaktera")
	private String naziv;
	
	/**
     * Sorta kulture.
     * 
     * Polje je obavezno i može imati najviše 50 karaktera.
     * 
     */
	@NotBlank(message = "Sorta kulture je obavezna")
	@Size(max=50, message = "Sorta moze imati najvise 50 karaktera")
	private String sorta;

	 /**
     * Kreira DTO kulture sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi kada je potrebno referencirati kulturu
     * samo putem njenog identifikatora.
     * 
     *
     * @param kulturaID jedinstveni identifikator kulture
     */
	public KulturaDto(Long kulturaID) {
		super();
		this.kulturaID = kulturaID;
	}	
	
	
}
