package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StavkaSetve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double cena;

	private LocalDate datum;

	@ManyToOne
	@JoinColumn(name = "setvaID")
	private Setva setva;

	@ManyToOne
	@JoinColumn(name = "aktivnostID")
	private Aktivnost aktivnost;

	public StavkaSetve(Long id) {

		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Setva getSetva() {
		return setva;
	}

	public void setSetva(Setva setva) {
		this.setva = setva;
	}

	public Aktivnost getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(Aktivnost aktivnost) {
		this.aktivnost = aktivnost;
	}

	@Override
	public String toString() {
		return "StavkaSetve [id=" + id + ", cena=" + cena + ", datum=" + datum + ", setva=" + setva + ", aktivnost="
				+ aktivnost + "]";
	}

	
}
