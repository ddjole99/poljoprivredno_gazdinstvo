package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParcelaDto {

	private Long parcelaID;

	@NotBlank(message = "Naziv parcele je obavezan")
	@Size(max=50, message = "Naziv parcele moze imati najvise 50 karaktera")
	private String naziv;

	@NotBlank(message = "Lokacija parcele je obavezna")
	@Size(max=50, message = "Lokacija moze imati najvise 50 karaktera")
	private String lokacija;

	@NotNull(message = "Povrsina parcele je obavezna")
	@Positive(message = "Povrsina mora biti veca od nula")
	private Double povrsina;
	
	@NotNull(message = "Tip zemljista je obavezan")
	private Long tipZemljista;
	

	public ParcelaDto(Long parcelaID) {
		super();
		this.parcelaID = parcelaID;
	}

	
	
}
