package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;

/**
 * Repozitorijum za upravljanje {@link Parcela} entitetima.
 * <p>
 * Ovaj interfejs proširuje {@link JpaRepository} i obezbeđuje
 * osnovne CRUD operacije nad parcelama u sistemu
 * poljoprivrednog gazdinstva.
 * </p>
 *
 * <p>
 * Implementaciju repozitorijuma automatski generiše Spring Data JPA
 * na osnovu definisanog interfejsa.
 * </p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela
 */
@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long>{
	
}
