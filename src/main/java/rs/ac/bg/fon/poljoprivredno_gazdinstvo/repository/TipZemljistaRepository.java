package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;

/**
 * Repozitorijum za upravljanje {@link TipZemljista} entitetima.
 * <p>
 * Ovaj interfejs proširuje {@link JpaRepository} i obezbeđuje
 * osnovne CRUD operacije nad tipovima zemljišta u sistemu
 * poljoprivrednog gazdinstva.
 * </p>
 *
 * <p>
 * Implementaciju repozitorijuma automatski generiše Spring Data JPA
 * na osnovu definisanog interfejsa.
 * </p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
 */
@Repository
public interface TipZemljistaRepository extends JpaRepository<TipZemljista, Long>{

}
