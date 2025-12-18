package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {

	@NotBlank(message = "Stara lozinka je obavezna")
	private String oldPassword;

	@NotBlank(message = "Nova lozinka je obavezna")
	@Size(min = 8, message = "Nova lozinka mora imati bar 8 karaktera")
	private String newPassword;

}
