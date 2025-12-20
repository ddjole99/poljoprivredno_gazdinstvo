package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Predstavlja opremu koja se koristi pri izvrsavanju poljoprivrednih aktivnosti.
 * 
 * Oprema moze biti povezana sa jednom ili vise aktivnosti, a ista aktivnost
 * moze koristiti vise razlicitih komada opreme, sto je modelovano
 * many-to-many relacijom.
 * 
 *
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Oprema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long opremaID;

	private String naziv;

	private String tipOpreme;

	@ManyToMany(mappedBy = "oprema")
	private List<Aktivnost> aktivnosti = new ArrayList<Aktivnost>();

	/**
     * Vraca jedinstveni identifikator opreme.
     *
     * @return identifikator opreme
     */
	public Long getOpremaID() {
		return opremaID;
	}

	/**
     * Postavlja jedinstveni identifikator opreme.
     *
     * @param opremaID identifikator opreme
     */
	public void setOpremaID(Long opremaID) {
		this.opremaID = opremaID;
	}

	 /**
     * Vraca naziv opreme.
     *
     * @return naziv opreme
     */
	public String getNaziv() {
		return naziv;
	}

	/**
     * Postavlja naziv opreme.
     *
     * @param naziv naziv opreme
     */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
     * Vraca tip opreme.
     *
     * @return tip opreme
     */
	public String getTipOpreme() {
		return tipOpreme;
	}

	/**
     * Postavlja tip opreme.
     *
     * @param tipOpreme tip opreme
     */
	public void setTipOpreme(String tipOpreme) {
		this.tipOpreme = tipOpreme;
	}

	/**
     * Vraca listu aktivnosti u kojima se oprema koristi.
     *
     * @return lista aktivnosti; prazna lista ako oprema nije povezana ni sa jednom
     *         aktivnošću (nikad {@code null})
     */
	public List<Aktivnost> getAktivnosti() {
		return aktivnosti;
	}

	 /**
     * Postavlja listu aktivnosti u kojima se oprema koristi.
     *
     * @param aktivnosti lista aktivnosti
     */
	public void setAktivnosti(List<Aktivnost> aktivnosti) {
		this.aktivnosti = aktivnosti;
	}

	/**
     * Vraca tekstualni prikaz opreme.
     *
     * @return string reprezentacija opreme
     */
	@Override
	public String toString() {
		return "Oprema [naziv=" + naziv + ", tipOpreme=" + tipOpreme + "]";
	}

	/**
     * Kreira opremu sa zadatim identifikatorom.
     * 
     * Konstruktor se koristi za referenciranje postojece opreme bez ucitavanja
     * svih podataka.
     *
     *
     * @param opremaID jedinstveni identifikator opreme
     */
	public Oprema(Long opremaID) {
		super();
		this.opremaID = opremaID;
	}

	 /**
     * Racuna hash kod opreme na osnovu njenog identifikatora.
     *
     * @return hash kod opreme
     */
	@Override
	public int hashCode() {
		return Objects.hash(opremaID);
	}

	 /**
     * Poredi dve instance opreme na osnovu identifikatora.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} objekti predstavljaju istu opremu;
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
		Oprema other = (Oprema) obj;
		return Objects.equals(opremaID, other.opremaID);
	}


}
