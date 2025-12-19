package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorUpdateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ChangePasswordRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Administrator;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.AdministratorMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AdministratorRepository;

@ExtendWith(MockitoExtension.class)
class AdministratorServiceTest {

	@Mock
	private AdministratorRepository administratorRepository;

	@Mock
	private AdministratorMapper administratorMapper;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private AdministratorService administratorService;

	@Test
	void testFindAllAdmine() {
		Administrator a1 = new Administrator(1L);
		Administrator a2 = new Administrator(2L);

		AdministratorDto d1 = new AdministratorDto(1L);
		d1.setUsername("u1");
		d1.setEmail("e1@test.com");

		AdministratorDto d2 = new AdministratorDto(2L);
		d2.setUsername("u2");
		d2.setEmail("e2@test.com");

		when(administratorRepository.findAll()).thenReturn(List.of(a1, a2));
		when(administratorMapper.toDto(any(Administrator.class))).thenReturn(d1, d2);

		List<AdministratorDto> result = administratorService.findAll();

		assertNotNull(result);
		assertEquals(2, result.size());

		verify(administratorRepository, times(1)).findAll();
		verify(administratorMapper, times(2)).toDto(any(Administrator.class));
		verifyNoMoreInteractions(administratorRepository, administratorMapper);
	}

	@Test
	void testFindByIdAdmin() {
		Long id = 1L;

		Administrator admin = new Administrator(id);

		AdministratorDto dto = new AdministratorDto(id);
		dto.setUsername("admin");
		dto.setEmail("admin@test.com");

		when(administratorRepository.findById(id)).thenReturn(Optional.of(admin));
		when(administratorMapper.toDto(admin)).thenReturn(dto);

		AdministratorDto result = administratorService.findById(id);

		assertNotNull(result);
		assertEquals(id, result.getAdministratorID());

		verify(administratorRepository).findById(id);
		verify(administratorMapper).toDto(admin);
	}

	@Test
	void testFindByIdNotFoundAdmin() {
		when(administratorRepository.findById(99L)).thenReturn(Optional.empty());

		AdministratorDto result = administratorService.findById(99L);

		assertNull(result);

		verify(administratorRepository).findById(99L);
		verifyNoInteractions(administratorMapper);
	}

	@Test
	void testCreateAdmin() {
		AdministratorCreateRequest req = new AdministratorCreateRequest();
		req.setEmail("admin@test.com");
		req.setUsername("admin");
		req.setPassword("pass12345");

		when(administratorRepository.existsByEmail(req.getEmail())).thenReturn(false);
		when(administratorRepository.existsByUsername(req.getUsername())).thenReturn(false);

		Administrator entity = new Administrator();
		entity.setEmail(req.getEmail());
		entity.setUsername(req.getUsername());

		when(administratorMapper.toEntity(req)).thenReturn(entity);
		when(passwordEncoder.encode(req.getPassword())).thenReturn("encodedPass");

		Administrator saved = new Administrator(1L);
		saved.setEmail(req.getEmail());
		saved.setUsername(req.getUsername());
		saved.setPasswordHash("encodedPass");

		when(administratorRepository.save(entity)).thenReturn(saved);

		AdministratorDto out = new AdministratorDto(1L);
		out.setEmail(req.getEmail());
		out.setUsername(req.getUsername());

		when(administratorMapper.toDto(saved)).thenReturn(out);

		AdministratorDto result = administratorService.create(req);

		assertNotNull(result);
		assertEquals(1L, result.getAdministratorID());
		assertEquals("encodedPass", entity.getPasswordHash());

		verify(administratorRepository).existsByEmail(req.getEmail());
		verify(administratorRepository).existsByUsername(req.getUsername());
		verify(administratorMapper).toEntity(req);
		verify(passwordEncoder).encode(req.getPassword());
		verify(administratorRepository).save(entity);
		verify(administratorMapper).toDto(saved);
	}

	@Test
	void testCreateEmailAlreadyExists() {
		AdministratorCreateRequest req = new AdministratorCreateRequest();
		req.setEmail("admin@test.com");
		req.setUsername("admin");
		req.setPassword("pass12345");

		when(administratorRepository.existsByEmail(req.getEmail())).thenReturn(true);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> administratorService.create(req));

		assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());

		verify(administratorRepository).existsByEmail(req.getEmail());
		verify(administratorRepository, never()).existsByUsername(anyString());
		verifyNoInteractions(administratorMapper);
		verifyNoInteractions(passwordEncoder);
	}

	@Test
	void testCreateUsernameAlreadyExists() {
		AdministratorCreateRequest req = new AdministratorCreateRequest();
		req.setEmail("admin@test.com");
		req.setUsername("admin");
		req.setPassword("pass12345");

		when(administratorRepository.existsByEmail(req.getEmail())).thenReturn(false);
		when(administratorRepository.existsByUsername(req.getUsername())).thenReturn(true);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> administratorService.create(req));

		assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());

		verify(administratorRepository).existsByEmail(req.getEmail());
		verify(administratorRepository).existsByUsername(req.getUsername());
		verifyNoInteractions(administratorMapper);
		verifyNoInteractions(passwordEncoder);
	}

	@Test
	void testUpdateAdmin() {
		Long id = 1L;

		Administrator existing = new Administrator(id);
		existing.setEmail("old@test.com");
		existing.setUsername("olduser");
		existing.setPasswordHash("hash");

		AdministratorUpdateRequest req = new AdministratorUpdateRequest();
		req.setEmail("new@test.com");
		req.setUsername("newuser");

		when(administratorRepository.findById(id)).thenReturn(Optional.of(existing));
		when(administratorRepository.existsByEmail(req.getEmail())).thenReturn(false);
		when(administratorRepository.existsByUsername(req.getUsername())).thenReturn(false);

		doNothing().when(administratorMapper).update(req, existing);

		when(administratorRepository.save(existing)).thenReturn(existing);

		AdministratorDto out = new AdministratorDto(id);
		out.setEmail(req.getEmail());
		out.setUsername(req.getUsername());

		when(administratorMapper.toDto(existing)).thenReturn(out);

		AdministratorDto result = administratorService.update(req, id);

		assertNotNull(result);
		assertEquals(id, result.getAdministratorID());

		verify(administratorRepository).findById(id);
		verify(administratorMapper).update(req, existing);
		verify(administratorRepository).save(existing);
		verify(administratorMapper).toDto(existing);
	}

	@Test
	void testUpdateNotFoundAdmin() {
		when(administratorRepository.findById(1L)).thenReturn(Optional.empty());

		AdministratorUpdateRequest req = new AdministratorUpdateRequest();
		req.setEmail("x@test.com");
		req.setUsername("x");

		assertThrows(EntityNotFoundException.class, () -> administratorService.update(req, 1L));

		verify(administratorRepository).findById(1L);
		verifyNoInteractions(administratorMapper);
	}

	@Test
	void testUpdateEmailAlreadyExists() {
		Long id = 1L;

		Administrator existing = new Administrator(id);
		existing.setEmail("old@test.com");
		existing.setUsername("olduser");

		AdministratorUpdateRequest req = new AdministratorUpdateRequest();
		req.setEmail("new@test.com"); // menjamo email
		req.setUsername("olduser"); // username isti

		when(administratorRepository.findById(id)).thenReturn(Optional.of(existing));
		when(administratorRepository.existsByEmail(req.getEmail())).thenReturn(true);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> administratorService.update(req, id));

		assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());

		verify(administratorRepository).findById(id);
		verify(administratorRepository).existsByEmail(req.getEmail());
		verify(administratorRepository, never()).existsByUsername(anyString());
		verifyNoInteractions(administratorMapper);
		verify(administratorRepository, never()).save(any());
	}

	@Test
	void testUpdateUsernameAlreadyExists() {
		Long id = 1L;

		Administrator existing = new Administrator(id);
		existing.setEmail("old@test.com");
		existing.setUsername("olduser");

		AdministratorUpdateRequest req = new AdministratorUpdateRequest();
		req.setEmail("old@test.com"); // email isti
		req.setUsername("newuser"); // menjamo username

		when(administratorRepository.findById(id)).thenReturn(Optional.of(existing));
		when(administratorRepository.existsByUsername(req.getUsername())).thenReturn(true);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> administratorService.update(req, id));

		assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());

		verify(administratorRepository).findById(id);
		verify(administratorRepository).existsByUsername(req.getUsername());
		verify(administratorRepository, never()).existsByEmail(anyString());
		verifyNoInteractions(administratorMapper);
		verify(administratorRepository, never()).save(any());
	}

	@Test
	void testDeleteAdmin() {
		Long id = 1L;
		Administrator existing = new Administrator(id);

		when(administratorRepository.findById(id)).thenReturn(Optional.of(existing));

		administratorService.delete(id);

		verify(administratorRepository).delete(existing);
	}

	@Test
	void testDeleteNotFoundAdmin() {
		when(administratorRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> administratorService.delete(1L));

		verify(administratorRepository).findById(1L);
		verify(administratorRepository, never()).delete(any());
	}

	@Test
	void testChangePassword() {
		Long id = 1L;

		Administrator existing = new Administrator(id);
		existing.setPasswordHash("encodedOld");

		ChangePasswordRequest req = new ChangePasswordRequest();
		req.setOldPassword("oldPass");
		req.setNewPassword("newPass123");

		when(administratorRepository.findById(id)).thenReturn(Optional.of(existing));
		when(passwordEncoder.matches(req.getOldPassword(), existing.getPasswordHash())).thenReturn(true);
		when(passwordEncoder.encode(req.getNewPassword())).thenReturn("encodedNew");
		when(administratorRepository.save(existing)).thenReturn(existing);

		administratorService.changePassword(id, req);

		assertEquals("encodedNew", existing.getPasswordHash());

		verify(administratorRepository).findById(id);
		verify(passwordEncoder).matches(req.getOldPassword(), "encodedOld");
		verify(passwordEncoder).encode(req.getNewPassword());
		verify(administratorRepository).save(existing);
	}

	@Test
	void testChangePasswordNotFound() {
		when(administratorRepository.findById(1L)).thenReturn(Optional.empty());

		ChangePasswordRequest req = new ChangePasswordRequest();
		req.setOldPassword("oldPass");
		req.setNewPassword("newPass123");

		assertThrows(EntityNotFoundException.class, () -> administratorService.changePassword(1L, req));

		verify(administratorRepository).findById(1L);
		verifyNoInteractions(passwordEncoder);
	}

	@Test
	void testChangePasswordWrongOldPassword() {
		Long id = 1L;

		Administrator existing = new Administrator(id);
		existing.setPasswordHash("encodedOld");

		ChangePasswordRequest req = new ChangePasswordRequest();
		req.setOldPassword("wrongOld");
		req.setNewPassword("newPass123");

		when(administratorRepository.findById(id)).thenReturn(Optional.of(existing));
		when(passwordEncoder.matches(req.getOldPassword(), existing.getPasswordHash())).thenReturn(false);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> administratorService.changePassword(id, req));

		assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());

		verify(administratorRepository).findById(id);
		verify(passwordEncoder).matches(req.getOldPassword(), existing.getPasswordHash());
		verify(passwordEncoder, never()).encode(anyString());
		verify(administratorRepository, never()).save(any());
	}
}
