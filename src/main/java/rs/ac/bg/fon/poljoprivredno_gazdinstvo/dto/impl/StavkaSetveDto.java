package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO za prenos podataka o stavci setve.
 * <p>
 * Ovaj DTO predstavlja jednu konkretnu aktivnost izvršenu u okviru
 * setve i sadrži podatke o datumu izvršenja, aktivnosti i ceni.
 * Koristi se kao deo {@link SetvaDto} objekta.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StavkaSetveDto {

	 /**
     * Jedinstveni identifikator stavke setve.
     */
	private Long id;
	
	/**
     * Datum izvršenja aktivnosti.
     * <p>
     * Datum ne sme biti u budućnosti.
     * </p>
     */
	@NotNull(message = "Datum aktivnost je obavezan")
	@PastOrPresent(message = "Datum atkivnosti ne sme biti u buducnosti")
	private LocalDate datum;
		
	/**
     * Identifikator aktivnosti koja je izvršena koji ne sme
     * biti null.
     */
	@NotNull(message = "Aktivnost je obavezna")
	private Long aktivnostID;
	
	/**
     * Cena aktivnosti izvršene u okviru stavke setve.
     * <p>
     * Vrednost mora biti pozitivna i veća od nule.
     * </p>
     */
	@NotNull(message = "Cena aktivnosti je obavezna")
	@Positive(message = "Cena mora biti veca od 0")
	private Double cena;

	
	
	
}
