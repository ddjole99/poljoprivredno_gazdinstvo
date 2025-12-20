package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prijavu administratora na sistem.
 * <p>
 * Ovaj objekat se koristi kao telo zahteva prilikom autentifikacije
 * administratora putem REST API-ja. Sadrži kredencijale potrebne
 * za proveru identiteta korisnika.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministratorLoginRequest {

	/**
     * Email adresa administratora.
     * 
     * Polje je obavezno, mora biti u validnom email formatu i
     * može imati najviše 50 karaktera.
     * 
     */
	@NotBlank(message = "Email je obavezan")
	@Email(message = "Email nije validan")
	@Size(max = 50, message = "Email moze imati najvise 50 karaktera")
	private String email;
	
	 /**
     * Lozinka administratora u nešifrovanom obliku.
     * 
     * Polje je obavezno i mora imati najmanje 8 karaktera.
     * Lozinka se koristi iskljucivo za autentifikaciju i
     * nece biti direktno cuvana u sistemu.
     * 
     */
	@NotBlank(message = "Lozinka je obavezna")
	@Size(min = 8, message = "Lozinka mora imati 6 karaktera")
	private String password;
}
