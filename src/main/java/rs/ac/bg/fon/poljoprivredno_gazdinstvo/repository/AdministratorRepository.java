package rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	
	boolean existsByEmail(String email);
	
	boolean existsByUsername(String username);
	
	
	Optional<Administrator> findByEmail(String email);
}
