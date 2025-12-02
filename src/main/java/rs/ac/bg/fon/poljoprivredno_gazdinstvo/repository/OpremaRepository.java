package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;

@Repository
public interface OpremaRepository extends JpaRepository<Oprema, Long>{
}
