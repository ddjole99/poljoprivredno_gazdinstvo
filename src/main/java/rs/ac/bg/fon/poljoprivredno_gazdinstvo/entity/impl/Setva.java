package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja proces setve određene kulture na konkretnoj parceli.
 * 
 * Setva je vremenski ogranicen proces koji ima definisan period trajanja,
 * status, odgovornog administratora i skup stavki setve koje detaljno opisuju
 * izvrsene aktivnosti u okviru setve.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.StavkaSetve
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Setva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long setvaID;

	private LocalDate datumPocetka;

	private LocalDate datumZavrsetka;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "administratorID")
	private Administrator administrator;

	@ManyToOne
	@JoinColumn(name = "parcelaID")
	private Parcela parcela;

	@ManyToOne
	@JoinColumn(name = "kulturaID")
	private Kultura kultura;
	
	@OneToMany(mappedBy = "setva", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StavkaSetve> stavkeSetve=new ArrayList<StavkaSetve>();

	/**
     * Kreira setvu sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi za referenciranje postojece setve bez ucitavanja
     * kompletnih podataka.
     * 
     *
     * @param setvaID jedinstveni identifikator setve
     */
	public Setva(Long setvaID) {
		super();
		this.setvaID = setvaID;
	}

	 /**
     * Vraca jedinstveni identifikator setve.
     *
     * @return identifikator setve
     */
	public Long getSetvaID() {
		return setvaID;
	}

	 /**
     * Postavlja jedinstveni identifikator setve.
     *
     * @param setvaID identifikator setve
     */
	public void setSetvaID(Long setvaID) {
		this.setvaID = setvaID;
	}

	/**
     * Vraca datum pocetka setve.
     *
     * @return datum pocetka setve
     */
	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}

	/**
     * Postavlja datum pocetka setve.
     *
     * @param datumPocetka datum pocetka setve
     */
	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	/**
     * Vraca datum zavrsetka setve.
     *
     * @return datum zavrsetka setve
     */
	public LocalDate getDatumZavrsetka() {
		return datumZavrsetka;
	}

	 /**
     * Postavlja datum zavrsetka setve.
     *
     * @param datumZavrsetka datum zavrsetka setve
     */
	public void setDatumZavrsetka(LocalDate datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	 /**
     * Vraca trenutni status setve.
     *
     * @return status setve
     *
     * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Status
     */
	public Status getStatus() {
		return status;
	}

	/**
     * Postavlja trenutni status setve.
     *
     * @param status status setve
     */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
     * Vraca administratora odgovornog za setvu.
     *
     * @return administrator setve
     */
	public Administrator getAdministrator() {
		return administrator;
	}

	/**
     * Postavlja administratora odgovornog za setvu.
     *
     * @param administrator administrator setve
     */
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	/**
     * Vraca parcelu na kojoj se setva izvršava.
     *
     * @return parcela setve
     */
	public Parcela getParcela() {
		return parcela;
	}

	 /**
     * Postavlja parcelu na kojoj se setva izvršava.
     *
     * @param parcela parcela setve
     */
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	/**
     * Vraca kulturu koja se seje.
     *
     * @return kultura setve
     */
	public Kultura getKultura() {
		return kultura;
	}

	 /**
     * Postavlja kulturu koja se seje.
     *
     * @param kultura kultura setve
     */
	public void setKultura(Kultura kultura) {
		this.kultura = kultura;
	}

	/**
     * Vraca listu stavki setve.
     *
     * @return lista stavki setve; prazna lista ako nema stavki (nikad {@code null})
     */
	public List<StavkaSetve> getStavkeSetve() {
		return stavkeSetve;
	}

	 /**
     * Postavlja listu stavki setve.
     *
     * @param stavkeSetve lista stavki setve
     */
	public void setStavkeSetve(List<StavkaSetve> stavkeSetve) {
		this.stavkeSetve = stavkeSetve;
	}

	 /**
     * Vraca tekstualni prikaz setve.
     *
     * @return string reprezentacija setve
     */
	@Override
	public String toString() {
		return "Setva [setvaID=" + setvaID + ", datumPocetka=" + datumPocetka + ", datumZavrsetka=" + datumZavrsetka
				+ ", status=" + status + ", administrator=" + administrator + ", parcela=" + parcela + ", kultura="
				+ kultura + ", stavkeSetve=" + stavkeSetve + "]";
	}

	/**
     * Racuna hash kod setve na osnovu njenog identifikatora.
     *
     * @return hash kod setve
     */
	@Override
	public int hashCode() {
		return Objects.hash(setvaID);
	}

	 /**
     * Poredi dve setve na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} objekti predstavljaju istu setvu;
     *         {@code false}objekti razliciti
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setva other = (Setva) obj;
		return Objects.equals(setvaID, other.setvaID);
	}
	
	
	
}
