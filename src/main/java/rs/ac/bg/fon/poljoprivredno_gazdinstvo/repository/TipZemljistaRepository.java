package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;

/**
 * Repozitorijum za upravljanje {@link TipZemljista} entitetima.
 * 
 * Ovaj interfejs prosiruje {@link JpaRepository} i obezbedjuje
 * osnovne CRUD operacije nad tipovima zemljista u sistemu
 * poljoprivrednog gazdinstva.
 * 
 *
 * Implementaciju repozitorijuma automatski generise Spring Data JPA
 * na osnovu definisanog interfejsa.
 * 
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista
 */
@Repository
public interface TipZemljistaRepository extends JpaRepository<TipZemljista, Long>{

}
