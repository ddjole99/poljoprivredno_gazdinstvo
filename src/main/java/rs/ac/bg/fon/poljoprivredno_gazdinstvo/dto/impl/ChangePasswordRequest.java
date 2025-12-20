package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za promenu lozinke administratora.
 * <p>
 * Ovaj objekat se koristi kao telo zahteva prilikom promene lozinke
 * postojećeg administratora. Sadrži staru lozinku radi verifikacije
 * identiteta i novu lozinku koja će zameniti postojeću.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {

	 /**
     * Trenutna (stara) lozinka administratora.
     * 
     * Polje je obavezno i koristi se za proveru identiteta
     * pre promene lozinke.
     * 
     */
	@NotBlank(message = "Stara lozinka je obavezna")
	private String oldPassword;

	/**
     * Nova lozinka administratora.
     * 
     * Polje je obavezno i mora imati najmanje 8 karaktera.
     * Nova lozinka ce biti hesirana pre cuvanja u sistemu.
     * 
     */
	@NotBlank(message = "Nova lozinka je obavezna")
	@Size(min = 8, message = "Nova lozinka mora imati bar 8 karaktera")
	private String newPassword;

}
