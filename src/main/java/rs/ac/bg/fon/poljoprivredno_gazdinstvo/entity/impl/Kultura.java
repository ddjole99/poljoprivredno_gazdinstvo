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
 * <p>
 * Kultura je definisana nazivom (npr. pšenica, kukuruz) i sortom.
 * </p>
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
     * Vraća jedinstveni identifikator kulture.
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
     * Vraća naziv kulture.
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
     * Vraća sortu kulture.
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
     * Vraća tekstualni prikaz kulture.
     * <p>
     * Prikaz se sastoji od naziva i sorte kulture.
     * </p>
     *
     * @return string reprezentacija kulture
     */
	@Override
	public String toString() {
		return naziv + " " + sorta;
	}

	/**
     * Kreira kulturu sa zadatim identifikatorom.
     * <p>
     * Konstruktor se koristi za referenciranje postojeće kulture
     * bez učitavanja kompletnih podataka.
     * </p>
     *
     * @param kulturaID jedinstveni identifikator kulture
     */
	public Kultura(Long kulturaID) {
		super();
		this.kulturaID = kulturaID;
	}

	/**
     * Računa hash kod kulture na osnovu njenog identifikatora.
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
     *         {@code false} objekti različiti
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
