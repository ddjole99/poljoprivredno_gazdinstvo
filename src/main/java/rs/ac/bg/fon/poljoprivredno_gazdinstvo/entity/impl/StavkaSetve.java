package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja stavku setve, odnosno konkretnu aktivnost izvrsenu
 * u okviru određene setve.
 * 
 * Stavka setve povezuje setvu i aktivnost i sadrzi dodatne podatke
 * kao sto su datum izvrsenja i cena aktivnosti. Ova klasa predstavlja
 * asocijativni entitet izmedju {@link Setva} i {@link Aktivnost}.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost
 */
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

	 /**
     * Kreira stavku setve sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi za referenciranje postojece stavke setve
     * bez ucitavanja kompletnih podataka.
     * 
     *
     * @param id jedinstveni identifikator stavke setve
     */
	public StavkaSetve(Long id) {

		this.id = id;
	}

	/**
     * Vraća jedinstveni identifikator stavke setve.
     *
     * @return identifikator stavke setve
     */
	public Long getId() {
		return id;
	}

	/**
     * Postavlja jedinstveni identifikator stavke setve.
     *
     * @param id identifikator stavke setve
     */
	public void setId(Long id) {
		this.id = id;
	}

	 /**
     * Vraca cenu aktivnosti u okviru stavke setve.
     *
     * @return cena aktivnosti
     */
	public Double getCena() {
		return cena;
	}

	/**
     * Postavlja cenu aktivnosti u okviru stavke setve.
     *
     * @param cena cena aktivnosti; mora biti nenegativna
     */
	public void setCena(Double cena) {
		this.cena = cena;
	}

	/**
     * Vraca datum izvrsenja aktivnosti.
     *
     * @return datum izvrsenja aktivnosti
     */
	public LocalDate getDatum() {
		return datum;
	}

	/**
     * Postavlja datum izvrsenja aktivnosti.
     *
     * @param datum datum izvrsenja aktivnosti
     */
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	/**
     * Vraca setvu kojoj stavka pripada.
     *
     * @return setva stavke
     */
	public Setva getSetva() {
		return setva;
	}

	/**
     * Postavlja setvu kojoj stavka pripada.
     *
     * @param setva setva stavke
     */
	public void setSetva(Setva setva) {
		this.setva = setva;
	}

	/**
     * Vrca aktivnost koja je izvrsena u okviru stavke setve.
     *
     * @return aktivnost stavke
     */
	public Aktivnost getAktivnost() {
		return aktivnost;
	}

	/**
     * Postavlja aktivnost koja se izvrsava u okviru stavke setve.
     *
     * @param aktivnost aktivnost stavke
     */
	public void setAktivnost(Aktivnost aktivnost) {
		this.aktivnost = aktivnost;
	}

	 /**
     * Vraća tekstualni prikaz stavke setve.
     *
     * @return string reprezentacija stavke setve
     */
	@Override
	public String toString() {
		return "StavkaSetve [id=" + id + ", cena=" + cena + ", datum=" + datum + ", setva=" + setva + ", aktivnost="
				+ aktivnost + "]";
	}

	 /**
     * Racuna hash kod stavke setve na osnovu njenog identifikatora.
     *
     * @return hash kod stavke setve
     */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
     * Poredi dve stavke setve na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true}  objekti predstavljaju istu stavku setve;
     *         {@code false} objekti razliciti
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StavkaSetve other = (StavkaSetve) obj;
		return Objects.equals(id, other.id);
	}

	
}
