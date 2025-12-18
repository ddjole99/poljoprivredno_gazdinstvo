package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AdministratorRepository;

@Service
@AllArgsConstructor
public class AdminDetalisService implements UserDetailsService {

	private final AdministratorRepository administratorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var admin = administratorRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronadjen"));

		return new User(admin.getEmail(), admin.getPasswordHash(), Collections.emptyList());
	}

}
