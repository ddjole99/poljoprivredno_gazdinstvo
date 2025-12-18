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

	public Long getOpremaID() {
		return opremaID;
	}

	public void setOpremaID(Long opremaID) {
		this.opremaID = opremaID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTipOpreme() {
		return tipOpreme;
	}

	public void setTipOpreme(String tipOpreme) {
		this.tipOpreme = tipOpreme;
	}

	public List<Aktivnost> getAktivnosti() {
		return aktivnosti;
	}

	public void setAktivnosti(List<Aktivnost> aktivnosti) {
		this.aktivnosti = aktivnosti;
	}

	@Override
	public String toString() {
		return "Oprema [naziv=" + naziv + ", tipOpreme=" + tipOpreme + "]";
	}

	public Oprema(Long opremaID) {
		super();
		this.opremaID = opremaID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(opremaID);
	}

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
