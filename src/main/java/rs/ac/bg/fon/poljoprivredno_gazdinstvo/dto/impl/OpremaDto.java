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
public class OpremaDto {
	
	private Long opremaID;
	
	@NotBlank(message = "Naziv opreme ne sme biti prazan")
	@Size(max = 50, message = "Naziv opreme moze imati najvise 50 karaktera")
	private String naziv;
	
	@NotBlank(message = "tipOpreme ne sme biti prazan")
	@Size(max = 50, message = "Tip opreme moze imati najvise 50 karaktera")
	private String tipOpreme;

	public OpremaDto(Long opremaID) {
		this.opremaID = opremaID;
	}
	
	
	
	
}
