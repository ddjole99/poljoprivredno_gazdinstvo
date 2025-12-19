package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;

/**
 * Repozitorijum za upravljanje {@link Oprema} entitetima.
 * <p>
 * Ovaj interfejs proširuje {@link JpaRepository} i obezbeđuje
 * osnovne CRUD operacije nad opremom koja se koristi u okviru
 * poljoprivrednih aktivnosti.
 * </p>
 *
 * <p>
 * Implementaciju repozitorijuma automatski generiše Spring Data JPA
 * na osnovu definisanog interfejsa.
 * </p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema
 */
@Repository
public interface OpremaRepository extends JpaRepository<Oprema, Long>{
}
