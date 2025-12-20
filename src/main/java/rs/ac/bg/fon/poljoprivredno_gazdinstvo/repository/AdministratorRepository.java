package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator;

/**
 * Repozitorijum za upravljanje {@link Administrator} entitetima.
 * 
 * Ovaj interfejs prosiruje {@link JpaRepository} i obezbedjuje
 * osnovne CRUD operacije nad administratorima, kao i dodatne
 * metode za proveru jedinstvenosti i pronalazenje administratora
 * na osnovu email adrese.
 * 
 *
 * Implementaciju ovog interfejsa automatski generiše Spring Data JPA
 * na osnovu naziva metoda.
 * 
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	
	 /**
     * Proverava da li administrator sa zadatom email adresom već postoji u sistemu.
     *
     * @param email email adresa administratora
     * @return {@code true} administrator sa datim email-om postoji;
     *         {@code false}administrator sa datim email-om ne postoji
     */
	boolean existsByEmail(String email);
	
	/**
     * Proverava da li administrator sa zadatim korisnickim imenom već postoji u sistemu.
     *
     * @param username korisnicko ime administratora
     * @return {@code true} administrator sa datim korisnickim imenom postoji;
     *         {@code false}administrator sa datim korisnickim imenom ne postoji
     */
	boolean existsByUsername(String username);
	
	/**
     * Pronalazi administratora na osnovu email adrese.
     *
     * @param email email adresa administratora
     * @return {@link Optional} koji sadrži pronadjenog administratora,
     *         ili je prazan ako administrator sa datim email-om ne postoji
     */
	Optional<Administrator> findByEmail(String email);
}
