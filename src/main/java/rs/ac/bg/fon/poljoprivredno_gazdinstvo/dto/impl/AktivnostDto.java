package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prenos podataka o aktivnosti.
 * <p>
 * Ovaj DTO se koristi za kreiranje, izmenu i prikaz aktivnosti
 * u okviru sistema poljoprivrednog gazdinstva. Pored osnovnih
 * podataka o aktivnosti, sadrži i identifikatore opreme povezane
 * sa aktivnošću.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AktivnostDto {
	
	/**
     * Jedinstveni identifikator aktivnosti.
     */
	private Long aktivnostID;
	
	/**
     * Naziv aktivnosti.
     * <p>
     * Polje je obavezno i može imati najviše 50 karaktera.
     * </p>
     */
	@NotBlank(message = "Naziv aktivnosti je obavezan")
	@Size(max=50, message = "Naziv aktivnosti moze imati najvise 50 karaktera")
	private String naziv;
	
	/**
     * Tip aktivnosti.
     * <p>
     * Polje je obavezno i predstavlja kategoriju kojoj aktivnost pripada
     * (npr. obrada zemljišta, navodnjavanje, zaštita useva).
     * </p>
     */
	@NotBlank(message = "Tip aktivnosti je obavezan")
	private String tipAktivnosti;
	
	/**
     * Lista identifikatora opreme povezane sa aktivnošću.
     * <p>
     * Lista mora sadržati bar jedan identifikator opreme.
     * </p>
     */
	@Size(min=1, message = "Morate izabrati bar jednu opremu")
	private List<Long> opremaIDs;
	
	/**
     * Kreira DTO aktivnosti sa zadatim identifikatorom.
     * <p>
     * Konstruktor se koristi za referenciranje aktivnosti
     * samo putem njenog identifikatora.
     * </p>
     *
     * @param aktivnostID jedinstveni identifikator aktivnosti
     */
	public AktivnostDto(Long aktivnostID) {
		this.aktivnostID = aktivnostID;
	}
	
	
}
