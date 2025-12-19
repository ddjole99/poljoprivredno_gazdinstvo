package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja parcelu poljoprivrednog zemljišta u okviru gazdinstva.
 * <p>
 * Parcela je definisana jedinstvenim nazivom, lokacijom, površinom i tipom
 * zemljišta. Svaka parcela ima tačno jedan tip zemljišta.
 * </p>
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "parcela_naziv", columnNames = "naziv")})
public class Parcela {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long parcelaID;
	
	@Column(nullable = false, length = 50)
	private String naziv;
	
	@Column(nullable = false, length = 100)
	private String lokacija;
	
	@Column(nullable = false)
	private Double povrsina;
	
	@ManyToOne
	@JoinColumn(name = "tipZemljistaID")
	private TipZemljista tipZemljista;

	/**
     * Kreira parcelu sa zadatim identifikatorom.
     * <p>
     * Konstruktor se koristi za referenciranje postojeće parcele bez učitavanja
     * svih podataka.
     * </p>
     *
     * @param parcelaID jedinstveni identifikator parcele
     */
	public Parcela(Long parcelaID) {
		super();
		this.parcelaID = parcelaID;
	}

	 /**
     * Vraća jedinstveni identifikator parcele.
     *
     * @return identifikator parcele
     */
	public Long getParcelaID() {
		return parcelaID;
	}

	 /**
     * Postavlja jedinstveni identifikator parcele.
     *
     * @param parcelaID identifikator parcele
     */
	public void setParcelaID(Long parcelaID) {
		this.parcelaID = parcelaID;
	}

	 /**
     * Vraća naziv parcele.
     *
     * @return naziv parcele
     */
	public String getNaziv() {
		return naziv;
	}

	/**
     * Postavlja naziv parcele.
     *
     * @param naziv naziv parcele
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
     * Vraća lokaciju parcele.
     *
     * @return lokacija parcele
     */
	public String getLokacija() {
		return lokacija;
	}

	/**
     * Postavlja lokaciju parcele.
     *
     * @param lokacija lokacija parcele
     */
	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	 /**
     * Vraća površinu parcele izraženu u hektarima.
     *
     * @return površina parcele
     */
	public Double getPovrsina() {
		return povrsina;
	}

	 /**
     * Postavlja površinu parcele.
     *
     * @param povrsina površina parcele u hektarima; mora biti pozitivna
     */
	public void setPovrsina(Double povrsina) {
		this.povrsina = povrsina;
	}

	/**
     * Vraća tip zemljišta kome parcela pripada.
     *
     * @return tip zemljišta parcele
     *
     * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
     */
	public TipZemljista getTipZemljista() {
		return tipZemljista;
	}

	 /**
     * Postavlja tip zemljišta kome parcela pripada.
     *
     * @param tipZemljista tip zemljišta
     */
	public void setTipZemljista(TipZemljista tipZemljista) {
		this.tipZemljista = tipZemljista;
	}

	 /**
     * Vraća tekstualni prikaz parcele.
     *
     * @return string reprezentacija parcele
     */
	@Override
	public String toString() {
		return "Parcela [naziv=" + naziv + ", lokacija=" + lokacija + ", povrsina=" + povrsina + ", tipZemljista="
				+ tipZemljista + "]";
	}

	/**
     * Računa hash kod parcele na osnovu njenog identifikatora.
     *
     * @return hash kod parcele
     */
	@Override
	public int hashCode() {
		return Objects.hash(parcelaID);
	}

	/**
     * Poredi dve parcele na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true}  objekti predstavljaju istu parcelu;
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
		Parcela other = (Parcela) obj;
		return Objects.equals(parcelaID, other.parcelaID);
	}

	
}
