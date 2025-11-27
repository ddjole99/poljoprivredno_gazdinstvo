package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StavkaSetveDto {

	private Long id;
	
	@NotNull(message = "Datum aktivnost je obavezan")
	@PastOrPresent(message = "Datum atkivnosti ne sme biti u buducnosti")
	private LocalDate datum;
		
	@NotNull(message = "Aktivnost je obavezna")
	private Long aktivnostID;
	
	@NotNull(message = "Cena aktivnosti je obavezna")
	@Positive(message = "Cena mora biti veca od 0")
	private Double cena;

	
	
	
}
