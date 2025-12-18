package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministratorLoginRequest {

	@NotBlank(message = "Email je obavezan")
	@Email(message = "Email nije validan")
	@Size(max = 50, message = "Email moze imati najvise 50 karaktera")
	private String email;
	
	@NotBlank(message = "Lozinka je obavezna")
	@Size(min = 8, message = "Lozinka mora imati 6 karaktera")
	private String password;
}
