package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za kreiranje novog administratora sistema.
 * <p>
 * Ovaj objekat se koristi kao telo zahteva prilikom registracije ili
 * kreiranja administratora putem REST API-ja. Sadrži podatke potrebne
 * za validaciju i bezbedno kreiranje administratorskog naloga.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministratorCreateRequest {

	/**
     * Korisničko ime administratora.
     * <p>
     * Polje je obavezno i može imati najviše 50 karaktera.
     * </p>
     */
	@NotBlank(message = "Korisnicko ime je obavezno")
	@Size(max = 50, message = "Korisnicko ime moze imati najvise 50 karaktera")
	private String username;

	/**
     * Email adresa administratora.
     * <p>
     * Polje je obavezno, mora biti u validnom email formatu i
     * može imati najviše 50 karaktera.
     * </p>
     */
	@NotBlank(message = "Email je obavezan")
	@Email(message = "Email nije validan")
	@Size(max = 50, message = "Email moze imati najvise 50 karaktera")
	private String email;

	/**
     * Lozinka administratora u nešifrovanom obliku.
     * <p>
     * Polje je obavezno i mora imati najmanje 8 karaktera.
     * Lozinka će biti heširana pre čuvanja u bazi.
     * </p>
     */
	@NotBlank(message = "Lozinka je obavezna")
	@Size(min = 8, message = "Lozinka mora imati 6 karaktera")
	private String password;
}
