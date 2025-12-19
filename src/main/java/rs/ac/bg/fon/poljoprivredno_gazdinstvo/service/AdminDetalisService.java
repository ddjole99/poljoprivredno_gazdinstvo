package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AdministratorRepository;

/**
 * Implementacija {@link UserDetailsService} interfejsa za potrebe
 * Spring Security autentifikacije administratora.
 * <p>
 * Ova klasa omogućava učitavanje podataka o administratoru na osnovu
 * email adrese, koja se koristi kao korisničko ime u sistemu.
 * </p>
 *
 * <p>
 * Spring Security automatski poziva metodu
 * {@link #loadUserByUsername(String)} tokom procesa autentifikacije.
 * </p>
 *
 * @see org.springframework.security.core.userdetails.UserDetailsService
 * @see org.springframework.security.core.userdetails.User
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AdministratorRepository
 */
@Service
@AllArgsConstructor
public class AdminDetalisService implements UserDetailsService {

	private final AdministratorRepository administratorRepository;

	/**
     * Učitava detalje administratora na osnovu email adrese.
     * <p>
     * Metoda se koristi u procesu autentifikacije i vraća objekat
     * tipa {@link UserDetails} koji sadrži potrebne informacije
     * za proveru identiteta korisnika.
     * </p>
     *
     * @param email email adresa administratora koja se koristi kao korisničko ime
     * @return {@link UserDetails} objekat koji sadrži email, heširanu lozinku
     *         i listu autorizacija (trenutno praznu)
     *
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     *         ako administrator sa zadatom email adresom ne postoji u sistemu
     */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var admin = administratorRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronadjen"));

		return new User(admin.getEmail(), admin.getPasswordHash(), Collections.emptyList());
	}

}
