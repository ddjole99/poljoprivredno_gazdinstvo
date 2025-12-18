package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorUpdateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ChangePasswordRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.AdministratorMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AdministratorRepository;

@AllArgsConstructor
@Service
public class AdministratorService {

	private final AdministratorRepository administratorRepository;
	private final AdministratorMapper administratorMapper;
	private final PasswordEncoder passwordEncoder;

	public List<AdministratorDto> findAll() {
		return administratorRepository.findAll().stream().map(administratorMapper::toDto).toList();
	}

	public AdministratorDto findById(Long id) {
		return administratorRepository.findById(id).map(administratorMapper::toDto).orElse(null);
	}

	public AdministratorDto create(@Valid AdministratorCreateRequest req) {
		if (administratorRepository.existsByEmail(req.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email je vec registrovan");
		}

		if (administratorRepository.existsByUsername(req.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Korisnicko ime je vec zauzeto");
		}

		var admin = administratorMapper.toEntity(req);
		admin.setPasswordHash(passwordEncoder.encode(req.getPassword()));

		var saved = administratorRepository.save(admin);

		return administratorMapper.toDto(saved);
	}

	public AdministratorDto update(@Valid AdministratorUpdateRequest req, Long id) {
		var existing = administratorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Administrator sa id=" + id + " ne postoji"));

		if (!existing.getEmail().equals(req.getEmail()) && administratorRepository.existsByEmail(req.getEmail()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email je vec zauzet");

		if (!existing.getUsername().equals(req.getUsername())
				&& administratorRepository.existsByUsername(req.getUsername()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username je vec zauzet");

		administratorMapper.update(req, existing);

		var saved = administratorRepository.save(existing);

		return administratorMapper.toDto(saved);
	}

	public void delete(Long id) {
		var existing=administratorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Administrator sa id="+id+" ne postoji"));
		
		administratorRepository.delete(existing);
		
	}

	public void changePassword(Long id, @Valid ChangePasswordRequest req) {
		var existing=administratorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Administrator sa id="+id+" ne postoji"));
		
		if(!passwordEncoder.matches(req.getOldPassword(), existing.getPasswordHash()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Stara lozinka nije ispravna");
		
		existing.setPasswordHash(passwordEncoder.encode(req.getNewPassword()));
		
		administratorRepository.save(existing);
		
	}

}
