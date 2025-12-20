package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja poljoprivrednu kulturu koja se uzgaja na parcelama
 * u okviru poljoprivrednog gazdinstva.
 * 
 * Kultura je definisana nazivom (npr. pšenica, kukuruz) i sortom.
 * 
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kultura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kulturaID;

	private String naziv;

	private String sorta;

	 /**
     * Vraca jedinstveni identifikator kulture.
     *
     * @return identifikator kulture
     */
	public Long getKulturaID() {
		return kulturaID;
	}

	/**
     * Postavlja jedinstveni identifikator kulture.
     *
     * @param kulturaID identifikator kulture
     */
	public void setKulturaID(Long kulturaID) {
		this.kulturaID = kulturaID;
	}

	/**
     * Vraca naziv kulture.
     *
     * @return naziv kulture
     */
	public String getNaziv() {
		return naziv;
	}

	/**
     * Postavlja naziv kulture.
     *
     * @param naziv naziv kulture
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	 /**
     * Vraca sortu kulture.
     *
     * @return sorta kulture
     */
	public String getSorta() {
		return sorta;
	}

	/**
     * Postavlja sortu kulture.
     *
     * @param sorta sorta kulture
     */
	public void setSorta(String sorta) {
		this.sorta = sorta;
	}

	/**
     * Vraca tekstualni prikaz kulture.
     * 
     * Prikaz se sastoji od naziva i sorte kulture.
     * 
     *
     * @return string reprezentacija kulture
     */
	@Override
	public String toString() {
		return naziv + " " + sorta;
	}

	/**
     * Kreira kulturu sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi za referenciranje postojece kulture
     * bez ucitavanja kompletnih podataka.
     * 
     *
     * @param kulturaID jedinstveni identifikator kulture
     */
	public Kultura(Long kulturaID) {
		super();
		this.kulturaID = kulturaID;
	}

	/**
     * Racuna hash kod kulture na osnovu njenog identifikatora.
     *
     * @return hash kod kulture
     */
	@Override
	public int hashCode() {
		return Objects.hash(kulturaID);
	}

	 /**
     * Poredi dve kulture na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true}  objekti predstavljaju istu kulturu;
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
		Kultura other = (Kultura) obj;
		return Objects.equals(kulturaID, other.kulturaID);
	}

}
