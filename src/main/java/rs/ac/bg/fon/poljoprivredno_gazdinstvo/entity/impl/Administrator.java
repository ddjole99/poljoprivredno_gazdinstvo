package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


/**
 * Predstavlja administratora sistema poljoprivrednog gazdinstva.
 * <p>
 * Administrator ima jedinstveno korisničko ime i email adresu i koristi se
 * za autentifikaciju i autorizaciju pristupa sistemu.
 * </p>
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "admin_username", columnNames = "username"),
		@UniqueConstraint(name = "admin_email", columnNames = "email") })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Administrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long administratorID;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 100)
	private String username;

	@Column(nullable = false)
	private String passwordHash;

	 /**
     * Kreira administratora sa zadatim identifikatorom.
     * <p>
     * Ovaj konstruktor se najčešće koristi kada je potrebno referencirati
     * postojećeg administratora bez učitavanja kompletnih podataka.
     * </p>
     *
     * @param administratorID jedinstveni identifikator administratora
     */
	public Administrator(Long administratorID) {
		this.administratorID = administratorID;
	}

	 /**
     * Vraća jedinstveni identifikator administratora.
     *
     * @return identifikator administratora
     */
	public Long getAdministratorID() {
		return administratorID;
	}

	/**
     * Setuje jedinstveni identifikator administratora.
     *
     * @param administratorID identifikator administratora
     */
	public void setAdministratorID(Long administratorID) {
		this.administratorID = administratorID;
	}

	/**
     * Vraća email adresu administratora.
     *
     * @return email adresa administratora
     */
	public String getEmail() {
		return email;
	}

	/**
     * Postavlja email adresu administratora.
     *
     * @param email email adresa administratora
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Vraća korisničko ime administratora.
     *
     * @return korisničko ime administratora
     */
	public String getUsername() {
		return username;
	}

	 /**
     * Postavlja korisničko ime administratora.
     *
     * @param username korisničko ime administratora
     */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
     * Vraća heš lozinku administratora.
     *
     * @return heširana lozinka administratora
     */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
     * Postavlja heširanu lozinku administratora.
     *
     * @param passwordHash heširana lozinka administratora
     */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
     * Računa hash kod administratora na osnovu email adrese i lozinke.
     *
     * @return hash kod administratora
     */
	@Override
	public int hashCode() {
		return Objects.hash(email, passwordHash);
	}

	/**
     * Poredi dva administratora na osnovu email adrese i heširane lozinke.
     *
     * @param  objekat sa kojim se poredi
     * @return {@code true}  objekti predstavljaju istog administratora;
     *         {@code false} objekti različiti
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		return Objects.equals(email, other.email) && Objects.equals(passwordHash, other.passwordHash);
	}

	/**
     * Vraća tekstualni prikaz administratora.
     *
     * @return string reprezentacija administratora
     */
	@Override
	public String toString() {
		return "Administrator [email=" + email + ", username=" + username + "]";
	}

	
	

}
