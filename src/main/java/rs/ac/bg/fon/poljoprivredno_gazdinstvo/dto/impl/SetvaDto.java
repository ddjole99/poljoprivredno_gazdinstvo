package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Status;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SetvaDto {

	private Long setvaID;

	@NotNull(message = "Administrator je obavezan")
	private Long administratorID;

	@NotNull(message = "Kultura je obavezna")
	private Long kulturaID;

	@NotNull(message = "Parcela je obavezna")
	private Long parcelaID;

	@NotNull(message = "Datum pocetka je obavezan")
	private LocalDate datumPocetka;

	@NotNull(message = "Datum zavrsetka je obavezan")
	private LocalDate datumZavrsetka;
	
	@NotNull(message = "Status setve je obavezan")
	private Status status;
	
	@Valid
	private List<StavkaSetveDto> stavkeSetve=new ArrayList<StavkaSetveDto>();
	
	public SetvaDto(Long setvaID) {
		this.setvaID = setvaID;
	}

}
