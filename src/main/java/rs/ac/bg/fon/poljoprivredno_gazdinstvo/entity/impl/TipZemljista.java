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
 * Predstavlja tip poljoprivrednog zemljišta.
 * <p>
 * Tip zemljišta opisuje osnovne karakteristike zemljišta, kao što su naziv,
 * pH vrednost i nivo plodnosti, i koristi se za klasifikaciju parcela
 * u okviru poljoprivrednog gazdinstva.
 * </p>
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
     * Kreira tip zemljišta sa zadatim identifikatorom.
     * <p>
     * Konstruktor se koristi za referenciranje postojećeg tipa zemljišta
     * bez učitavanja kompletnih podataka.
     * </p>
     *
     * @param tipZemljistaID jedinstveni identifikator tipa zemljišta
     */
	public TipZemljista(Long tipZemljistaID) {
		this.tipZemljistaID = tipZemljistaID;
	}

	/**
     * Vraća jedinstveni identifikator tipa zemljišta.
     *
     * @return identifikator tipa zemljišta
     */
	public Long getTipZemljistaID() {
		return tipZemljistaID;
	}

	/**
     * Postavlja jedinstveni identifikator tipa zemljišta.
     *
     * @param tipZemljistaID identifikator tipa zemljišta
     */
	public void setTipZemljistaID(Long tipZemljistaID) {
		this.tipZemljistaID = tipZemljistaID;
	}

	/**
     * Vraća naziv tipa zemljišta.
     *
     * @return naziv tipa zemljišta
     */
	public String getNaziv() {
		return naziv;
	}

	/**
     * Postavlja naziv tipa zemljišta.
     *
     * @param naziv naziv tipa zemljišta
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
     * Vraća pH vrednost zemljišta.
     *
     * @return pH vrednost zemljišta
     */
	public Double getPhVrednost() {
		return phVrednost;
	}

	/**
     * Postavlja pH vrednost zemljišta.
     *
     * @param phVrednost pH vrednost zemljišta
     */
	public void setPhVrednost(Double phVrednost) {
		this.phVrednost = phVrednost;
	}

	/**
     * Vraća opis plodnosti zemljišta.
     *
     * @return plodnost zemljišta
     */
	public String getPlodnost() {
		return plodnost;
	}

	 /**
     * Postavlja opis plodnosti zemljišta.
     *
     * @param plodnost plodnost zemljišta
     */
	public void setPlodnost(String plodnost) {
		this.plodnost = plodnost;
	}

	 /**
     * Vraća tekstualni prikaz tipa zemljišta.
     *
     * @return naziv tipa zemljišta
     */
	@Override
	public String toString() {
		return naziv;
	}

	/**
     * Računa hash kod tipa zemljišta na osnovu identifikatora.
     *
     * @return hash kod tipa zemljišta
     */
	@Override
	public int hashCode() {
		return Objects.hash(tipZemljistaID);
	}

	/**
     * Poredi dva tipa zemljišta na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} objekti predstavljaju isti tip zemljišta;
     *         {@code false}objekti razičiti
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
