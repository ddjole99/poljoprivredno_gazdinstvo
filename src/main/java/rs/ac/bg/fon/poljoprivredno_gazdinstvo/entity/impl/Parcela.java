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

	
	public Parcela(Long parcelaID) {
		super();
		this.parcelaID = parcelaID;
	}

	public Long getParcelaID() {
		return parcelaID;
	}

	public void setParcelaID(Long parcelaID) {
		this.parcelaID = parcelaID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public Double getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(Double povrsina) {
		this.povrsina = povrsina;
	}

	public TipZemljista getTipZemljista() {
		return tipZemljista;
	}

	public void setTipZemljista(TipZemljista tipZemljista) {
		this.tipZemljista = tipZemljista;
	}

	@Override
	public String toString() {
		return "Parcela [naziv=" + naziv + ", lokacija=" + lokacija + ", povrsina=" + povrsina + ", tipZemljista="
				+ tipZemljista + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(parcelaID);
	}

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
