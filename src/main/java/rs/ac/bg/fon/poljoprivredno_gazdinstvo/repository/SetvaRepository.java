package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva;

/**
 * Repozitorijum za upravljanje {@link Setva} entitetima.
 * 
 * Ovaj interfejs prosiruje {@link JpaRepository} i obezbedjuje
 * osnovne CRUD operacije nad setvama u sistemu
 * poljoprivrednog gazdinstva.
 *
 * 
 * Implementaciju repozitorijuma automatski generise Spring Data JPA
 * na osnovu definisanog interfejsa.
 *
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Setva
 */
@Repository
public interface SetvaRepository extends JpaRepository<Setva, Long>{

}
