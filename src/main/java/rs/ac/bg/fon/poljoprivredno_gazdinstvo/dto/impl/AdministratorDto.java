package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prenos podataka o administratoru sistema.
 * <p>
 * Ovaj DTO se koristi za slanje podataka o administratoru ka klijentu
 * (npr. kao odgovor REST API-ja) ili za prenos već postojećih
 * administratorskih podataka unutar sistema.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministratorDto {

	/**
     * Jedinstveni identifikator administratora.
     */
	private Long administratorID;

	 /**
     * Korisnicko ime administratora.
     * 
     * Polje je obavezno i može imati najvise 50 karaktera.
     * 
     */
	@NotBlank(message = "Korisnicko ime je obavezno")
	@Size(max = 50, message = "Korisnicko ime moze imati najvise 50 karaktera")
	private String username;

	/**
     * Email adresa administratora.
     * 
     * Polje je obavezno, mora biti u validnom email formatu i
     * moze imati najvise 50 karaktera.
     * 
     */
	@NotBlank(message = "Email je obavezan")
	@Email(message = "Email nije validan")
	@Size(max = 50, message = "Email moze imati najvise 50 karaktera")
	private String email;

	/**
     * Kreira DTO administratora sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi kada je potrebno referencirati administratora
     * samo putem njegovog identifikatora, bez ostalih podataka.
     * 
     *
     * @param administratorID jedinstveni identifikator administratora
     */
	public AdministratorDto(Long administratorID) {
		this.administratorID = administratorID;
	}

}
