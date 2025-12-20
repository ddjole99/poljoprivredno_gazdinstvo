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
 * 
 * Ova klasa omogucava ucitavanje podataka o administratoru na osnovu
 * email adrese, koja se koristi kao korisnicko ime u sistemu.
 * 
 *
 * 
 * Spring Security automatski poziva metodu
 * {@link #loadUserByUsername(String)} tokom procesa autentifikacije.
 * 
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
     * Ucitava detalje administratora na osnovu email adrese.
     * 
     * Metoda se koristi u procesu autentifikacije i vraca objekat
     * tipa {@link UserDetails} koji sadrzi potrebne informacije
     * za proveru identiteta korisnika.
     * 
     *
     * @param email email adresa administratora koja se koristi kao korisnicko ime
     * @return {@link UserDetails} objekat koji sadrži email, hesiranu lozinku
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
