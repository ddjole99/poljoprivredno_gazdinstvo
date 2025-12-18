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

	
	public TipZemljista(Long tipZemljistaID) {
		this.tipZemljistaID = tipZemljistaID;
	}

	public Long getTipZemljistaID() {
		return tipZemljistaID;
	}

	public void setTipZemljistaID(Long tipZemljistaID) {
		this.tipZemljistaID = tipZemljistaID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getPhVrednost() {
		return phVrednost;
	}

	public void setPhVrednost(Double phVrednost) {
		this.phVrednost = phVrednost;
	}

	public String getPlodnost() {
		return plodnost;
	}

	public void setPlodnost(String plodnost) {
		this.plodnost = plodnost;
	}

	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipZemljistaID);
	}

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
