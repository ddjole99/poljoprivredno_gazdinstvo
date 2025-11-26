package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;

public interface AktivnostRepository extends JpaRepository<Aktivnost, Long> {

}
