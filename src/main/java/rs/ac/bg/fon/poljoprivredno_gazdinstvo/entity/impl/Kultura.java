package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kultura {

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
		return naziv+" "+sorta;
	}
	
	
	
}
