package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za izmenu podataka o administratoru sistema.
 * <p>
 * Ovaj objekat se koristi kao telo zahteva prilikom ažuriranja
 * postojećeg administratora putem REST API-ja. Sadrži podatke
 * koje je dozvoljeno menjati.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministratorUpdateRequest {

	/**
     * Novo korisnicko ime administratora.
     * 
     * Polje je obavezno i moze imati najviše 50 karaktera.
     * 
     */
	@NotBlank(message = "Korisnicko ime je obavezno")
	@Size(max = 50, message = "Korisnicko ime moze imati najvise 50 karaktera")
	private String username;

	/**
     * Nova email adresa administratora.
     *
     * Polje je obavezno, mora biti u validnom email formatu i
     * moze imati najvise 50 karaktera.
     * 
     */
	@NotBlank(message = "Email je obavezan")
	@Email(message = "Email nije validan")
	@Size(max = 50, message = "Email moze imati najvise 50 karaktera")
	private String email;
}
