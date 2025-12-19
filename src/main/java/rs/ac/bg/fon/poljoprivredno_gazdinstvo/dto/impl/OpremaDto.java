package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prenos podataka o opremi.
 * <p>
 * Ovaj DTO se koristi za kreiranje, izmenu i prikaz opreme
 * koja se koristi u okviru poljoprivrednih aktivnosti.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OpremaDto {
	
	/**
     * Jedinstveni identifikator opreme.
     */
	private Long opremaID;
	
	/**
     * Naziv opreme.
     * <p>
     * Polje je obavezno i može imati najviše 50 karaktera.
     * </p>
     */
	@NotBlank(message = "Naziv opreme ne sme biti prazan")
	@Size(max = 50, message = "Naziv opreme moze imati najvise 50 karaktera")
	private String naziv;
	
	/**
     * Tip opreme.
     * <p>
     * Polje je obavezno i opisuje kategoriju ili vrstu opreme
     * (npr. mehanizacija, alat, sistem za navodnjavanje).
     * </p>
     */
	@NotBlank(message = "tipOpreme ne sme biti prazan")
	@Size(max = 50, message = "Tip opreme moze imati najvise 50 karaktera")
	private String tipOpreme;

	/**
     * Kreira DTO opreme sa zadatim identifikatorom.
     * <p>
     * Konstruktor se koristi kada je potrebno referencirati opremu
     * samo putem njenog identifikatora.
     * </p>
     *
     * @param opremaID jedinstveni identifikator opreme
     */
	public OpremaDto(Long opremaID) {
		this.opremaID = opremaID;
	}
	
	
	
	
}
