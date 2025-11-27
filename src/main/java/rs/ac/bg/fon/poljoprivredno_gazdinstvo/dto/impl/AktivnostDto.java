package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

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
	private String naziv;
	private String opremaNaziv;
	
	public AktivnostDto(Long aktivnostID) {
		super();
		this.aktivnostID = aktivnostID;
	}
	
	
}
