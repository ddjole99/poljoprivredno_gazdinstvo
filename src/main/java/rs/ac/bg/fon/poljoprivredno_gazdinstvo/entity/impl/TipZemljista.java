package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja tip poljoprivrednog zemljista.
 * 
 * Tip zemljista opisuje osnovne karakteristike zemljista, kao što su naziv,
 * pH vrednost i nivo plodnosti, i koristi se za klasifikaciju parcela
 * u okviru poljoprivrednog gazdinstva.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "zemljiste_naziv", columnNames = "naziv")})
public class TipZemljista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tipZemljistaID;
	
	private String naziv;
	
	private Double phVrednost;
	
	private String plodnost;

	/**
     * Kreira tip zemljista sa zadatim identifikatorom.
     *
     * Konstruktor se koristi za referenciranje postojeceg tipa zemljista
     * bez ucitavanja kompletnih podataka.
     * 
     *
     * @param tipZemljistaID jedinstveni identifikator tipa zemljista
     */
	public TipZemljista(Long tipZemljistaID) {
		this.tipZemljistaID = tipZemljistaID;
	}

	/**
     * Vraca jedinstveni identifikator tipa zemljista.
     *
     * @return identifikator tipa zemljista
     */
	public Long getTipZemljistaID() {
		return tipZemljistaID;
	}

	/**
     * Postavlja jedinstveni identifikator tipa zemljista.
     *
     * @param tipZemljistaID identifikator tipa zemljista
     */
	public void setTipZemljistaID(Long tipZemljistaID) {
		this.tipZemljistaID = tipZemljistaID;
	}

	/**
     * Vraca naziv tipa zemljista.
     *
     * @return naziv tipa zemljista
     */
	public String getNaziv() {
		return naziv;
	}

	/**
     * Postavlja naziv tipa zemljista.
     *
     * @param naziv naziv tipa zemljista
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
     * Vraca pH vrednost zemljista.
     *
     * @return pH vrednost zemljista
     */
	public Double getPhVrednost() {
		return phVrednost;
	}

	/**
     * Postavlja pH vrednost zemljista.
     *
     * @param phVrednost pH vrednost zemljista
     */
	public void setPhVrednost(Double phVrednost) {
		this.phVrednost = phVrednost;
	}

	/**
     * Vraca opis plodnosti zemljista.
     *
     * @return plodnost zemljista
     */
	public String getPlodnost() {
		return plodnost;
	}

	 /**
     * Postavlja opis plodnosti zemljista.
     *
     * @param plodnost plodnost zemljista
     */
	public void setPlodnost(String plodnost) {
		this.plodnost = plodnost;
	}

	 /**
     * Vraca tekstualni prikaz tipa zemljista.
     *
     * @return naziv tipa zemljista
     */
	@Override
	public String toString() {
		return naziv;
	}

	/**
     * Racuna hash kod tipa zemljišta na osnovu identifikatora.
     *
     * @return hash kod tipa zemljista
     */
	@Override
	public int hashCode() {
		return Objects.hash(tipZemljistaID);
	}

	/**
     * Poredi dva tipa zemljista na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} objekti predstavljaju isti tip zemljista;
     *         {@code false}objekti raziciti
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipZemljista other = (TipZemljista) obj;
		return Objects.equals(tipZemljistaID, other.tipZemljistaID);
	}
	
	
	
}
