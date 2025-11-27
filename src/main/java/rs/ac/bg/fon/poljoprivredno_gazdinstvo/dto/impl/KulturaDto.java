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
public class KulturaDto {
	
	private Long kulturaID;
	
	@NotBlank(message = "Naziv kulture je obavezan")
	@Size(max=50, message = "Naziv kulture moze imati najvise 50 karaktera")
	private String naziv;
	
	@NotBlank(message = "Sorta kulture je obavezna")
	@Size(max=50, message = "Sorta moze imati najvise 50 karaktera")
	private String sorta;

	public KulturaDto(Long kulturaID) {
		super();
		this.kulturaID = kulturaID;
	}	
	
	
}
