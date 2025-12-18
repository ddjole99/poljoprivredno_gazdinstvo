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

	public Aktivnost(Long aktivnostID) {
		this.aktivnostID = aktivnostID;
	}

	public Long getAktivnostID() {
		return aktivnostID;
	}

	public void setAktivnostID(Long aktivnostID) {
		this.aktivnostID = aktivnostID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipAktivnosti getTipAktivnosti() {
		return tipAktivnosti;
	}

	public void setTipAktivnosti(TipAktivnosti tipAktivnosti) {
		this.tipAktivnosti = tipAktivnosti;
	}

	public List<Oprema> getOprema() {
		return oprema;
	}

	public void setOprema(List<Oprema> oprema) {
		this.oprema = oprema;
	}

	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aktivnostID);
	}

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
