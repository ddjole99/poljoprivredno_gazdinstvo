package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja aktivnost koja se može izvršavati u okviru poljoprivrednih radova
 * (npr. oranje, setva, đubrenje, prskanje).
 * 
 * Aktivnost ima naziv, tip aktivnosti i skup opreme koja može biti potrebna za
 * njeno izvršavanje. Veza sa opremom je realizovana kao many-to-many relacija.
 * 
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Aktivnost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aktivnostID;

	private String naziv;

	@Enumerated(EnumType.STRING)
	private TipAktivnosti tipAktivnosti;

	@ManyToMany
	@JoinTable(name = "aktivnost_oprema", joinColumns = @JoinColumn(name = "aktivnostID"), inverseJoinColumns = @JoinColumn(name = "opremaID"))
	private List<Oprema> oprema = new ArrayList<>();

	 /**
     * Kreira aktivnost sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi kada je potrebno referencirati postojeću aktivnost
     * (npr. pri povezivanju sa drugim entitetima) bez popunjavanja svih polja.
     * 
     *
     * @param aktivnostID jedinstveni identifikator aktivnosti
     */
	public Aktivnost(Long aktivnostID) {
		this.aktivnostID = aktivnostID;
	}

	/**
     * Vraća jedinstveni identifikator aktivnosti.
     *
     * @return identifikator aktivnosti
     */
	public Long getAktivnostID() {
		return aktivnostID;
	}

	/**
     * Postavlja jedinstveni identifikator aktivnosti.
     *
     * @param aktivnostID identifikator aktivnosti
     */
	public void setAktivnostID(Long aktivnostID) {
		this.aktivnostID = aktivnostID;
	}

	 /**
     * Vraća naziv aktivnosti.
     *
     * @return naziv aktivnosti
     */
	public String getNaziv() {
		return naziv;
	}

	/**
     * Postavlja naziv aktivnosti.
     *
     * @param naziv naziv aktivnosti
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
     * Vraća tip aktivnosti.
     *
     * @return tip aktivnosti
     *
     * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipAktivnosti
     */
	public TipAktivnosti getTipAktivnosti() {
		return tipAktivnosti;
	}

	/**
     * Postavlja tip aktivnosti.
     *
     * @param tipAktivnosti tip aktivnosti
     */
	public void setTipAktivnosti(TipAktivnosti tipAktivnosti) {
		this.tipAktivnosti = tipAktivnosti;
	}

	/**
     * Vraća listu opreme povezane sa aktivnošću.
     *
     * @return lista opreme; prazna lista ako nema povezane opreme (nikad {@code null})
     */
	public List<Oprema> getOprema() {
		return oprema;
	}

	/**
     * Postavlja listu opreme povezane sa aktivnoscu.
     *
     * @param oprema lista opreme; moze biti prazna, ali ne bi trebalo da bude {@code null}
     */
	public void setOprema(List<Oprema> oprema) {
		this.oprema = oprema;
	}

	 /**
     * Vraca tekstualni prikaz aktivnosti.
     * <p>
     *  Koristi se naziv aktivnosti kao njen najkraci prikaz.
     * </p>
     *
     * @return naziv aktivnosti u vidu teksta
     */
	@Override
	public String toString() {
		return naziv;
	}

	/**
     * Racuna hash kod aktivnosti na osnovu njenog identifikatora.
     *
     * @return hash kod aktivnosti
     */
	@Override
	public int hashCode() {
		return Objects.hash(aktivnostID);
	}

	/**
     * Poredi dve aktivnosti na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true}  objekti predstavljaju istu aktivnost;
     *         {@code false} objetki razliciti
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aktivnost other = (Aktivnost) obj;
		return Objects.equals(aktivnostID, other.aktivnostID);
	}

	
}
