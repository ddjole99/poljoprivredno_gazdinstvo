package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import java.util.List;

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
public class AktivnostDto {
	
	private Long aktivnostID;
	
	@NotBlank(message = "Naziv aktivnosti je obavezan")
	@Size(max=50, message = "Naziv aktivnosti moze imati najvise 50 karaktera")
	private String naziv;
	
	@NotBlank(message = "Tip aktivnosti je obavezan")
	private String tipAktivnosti;
	
	@Size(min=1, message = "Morate izabrati bar jednu opremu")
	private List<Long> opremaIDs;
	
	public AktivnostDto(Long aktivnostID) {
		this.aktivnostID = aktivnostID;
	}
	
	
}
