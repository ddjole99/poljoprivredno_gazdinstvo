package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;

/**
 * Repozitorijum za upravljanje {@link Oprema} entitetima.
 * 
 * Ovaj interfejs prosiruje {@link JpaRepository} i obezbedjuje
 * osnovne CRUD operacije nad opremom koja se koristi u okviru
 * poljoprivrednih aktivnosti.
 *
 * 
 * Implementaciju repozitorijuma automatski generiše Spring Data JPA
 * na osnovu definisanog interfejsa.
 * 
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema
 */
@Repository
public interface OpremaRepository extends JpaRepository<Oprema, Long>{
}
