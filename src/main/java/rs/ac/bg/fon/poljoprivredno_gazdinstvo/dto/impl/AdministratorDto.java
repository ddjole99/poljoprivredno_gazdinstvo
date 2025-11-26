package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministratorDto {
	// Stavio sam u svim dto klasama id jer nisam hteo da implementiram vise DTO tipova
	private Long administratorID;
	private String username;
	private String email;
	
	public AdministratorDto(Long administratorID) {
		this.administratorID = administratorID;
	}

	
}
