package rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO za prenos JWT tokena ka klijentu.
 * <p>
 * Ovaj objekat se koristi kao odgovor REST API-ja nakon uspešne
 * autentifikacije administratora. Sadrži JSON Web Token (JWT)
 * koji klijent koristi za autorizaciju narednih zahteva.
 * </p>
 */
@Data
@AllArgsConstructor
public class JwtResponse {
	
	 /**
     * JSON Web Token (JWT) za autentifikovanog korisnika.
     * <p>
     * Token se prosleđuje klijentu i koristi se u HTTP zaglavlju
     * {@code Authorization} sa prefiksom {@code Bearer}.
     * </p>
     */
	private String token;

}
