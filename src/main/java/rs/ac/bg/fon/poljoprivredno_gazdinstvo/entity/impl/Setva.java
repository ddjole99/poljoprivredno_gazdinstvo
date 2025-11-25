package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Setva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long setvaID;

	private LocalDate datumPocetka;

	private LocalDate datumZavrsetka;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "administratorID")
	private Administrator administrator;

	@ManyToOne
	@JoinColumn(name = "parcelaID")
	private Parcela parcela;

	@ManyToOne
	@JoinColumn(name = "kulturaID")
	private Kultura kultura;
	
	@OneToMany(mappedBy = "setva", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StavkaSetve> stavkeSetve=new ArrayList<StavkaSetve>();

	public Setva(Long setvaID) {
		super();
		this.setvaID = setvaID;
	}

	public Long getSetvaID() {
		return setvaID;
	}

	public void setSetvaID(Long setvaID) {
		this.setvaID = setvaID;
	}

	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDate getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(LocalDate datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Kultura getKultura() {
		return kultura;
	}

	public void setKultura(Kultura kultura) {
		this.kultura = kultura;
	}

	public List<StavkaSetve> getStavkeSetve() {
		return stavkeSetve;
	}

	public void setStavkeSetve(List<StavkaSetve> stavkeSetve) {
		this.stavkeSetve = stavkeSetve;
	}

	@Override
	public String toString() {
		return "Setva [setvaID=" + setvaID + ", datumPocetka=" + datumPocetka + ", datumZavrsetka=" + datumZavrsetka
				+ ", status=" + status + ", administrator=" + administrator + ", parcela=" + parcela + ", kultura="
				+ kultura + ", stavkeSetve=" + stavkeSetve + "]";
	}
	
	
}
