package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kultura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kulturaID;

	private String naziv;

	private String sorta;

	public Long getKulturaID() {
		return kulturaID;
	}

	public void setKulturaID(Long kulturaID) {
		this.kulturaID = kulturaID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSorta() {
		return sorta;
	}

	public void setSorta(String sorta) {
		this.sorta = sorta;
	}

	@Override
	public String toString() {
		return naziv + " " + sorta;
	}

	public Kultura(Long kulturaID) {
		super();
		this.kulturaID = kulturaID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(kulturaID);
	}

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
