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

	public Administrator(Long administratorID) {
		this.administratorID = administratorID;
	}

	public Long getAdministratorID() {
		return administratorID;
	}

	public void setAdministratorID(Long administratorID) {
		this.administratorID = administratorID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, passwordHash);
	}

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

	
	

}
