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

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.KulturaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Kultura;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.KulturaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.KulturaRepository;

@ExtendWith(MockitoExtension.class)
class KulturaServiceTest {

	@Mock
	private KulturaRepository kulturaRepository;

	@Mock
	private KulturaMapper kulturaMapper;

	@InjectMocks
	private KulturaService kulturaService;

	@Test
	void testFindAllKulture() {
		Kultura e1 = new Kultura(1L);
		Kultura e2 = new Kultura(2L);

		KulturaDto d1 = new KulturaDto();
		d1.setKulturaID(1L);
		KulturaDto d2 = new KulturaDto();
		d2.setKulturaID(2L);

		when(kulturaRepository.findAll()).thenReturn(List.of(e1, e2));
		when(kulturaMapper.toDto(e1)).thenReturn(d1);
		when(kulturaMapper.toDto(e2)).thenReturn(d2);

		List<KulturaDto> kulture = kulturaService.findAll();

		assertEquals(2, kulture.size());
		verify(kulturaRepository).findAll();
		verify(kulturaMapper).toDto(e1);
		verify(kulturaMapper).toDto(e2);
	}

	@Test
	void testFindByIdKulturu() {
		Long id = 1L;
		Kultura entity = new Kultura(id);
		KulturaDto dto = new KulturaDto();
		dto.setKulturaID(id);

		when(kulturaRepository.findById(id)).thenReturn(Optional.of(entity));
		when(kulturaMapper.toDto(entity)).thenReturn(dto);

		KulturaDto result = kulturaService.findById(id);

		assertNotNull(result);
		assertEquals(id, result.getKulturaID());
	}

	@Test
	void testFindByIdNotFoundKulturu() {
		when(kulturaRepository.findById(99L)).thenReturn(Optional.empty());

		KulturaDto result = kulturaService.findById(99L);

		assertNull(result);
		verify(kulturaRepository).findById(99L);
		verifyNoInteractions(kulturaMapper);
	}

	@Test
	void testSaveKulturu() {
		KulturaDto input = new KulturaDto();
		input.setNaziv("Kukuruz");

		Kultura entity = new Kultura();
		entity.setNaziv("Kukuruz");

		Kultura saved = new Kultura(1L);
		saved.setNaziv("Kukuruz");

		KulturaDto output = new KulturaDto();
		output.setKulturaID(1L);

		when(kulturaMapper.toEntity(input)).thenReturn(entity);
		when(kulturaRepository.save(entity)).thenReturn(saved);
		when(kulturaMapper.toDto(saved)).thenReturn(output);

		KulturaDto result = kulturaService.save(input);

		assertEquals(1L, result.getKulturaID());
	}

	@Test
	void testDeleteKulturu() {
		Kultura k = new Kultura(1L);
		when(kulturaRepository.findById(1L)).thenReturn(Optional.of(k));

		kulturaService.delete(1L);

		verify(kulturaRepository).delete(k);
	}

	@Test
	void testDeleteNotFoundKulturu() {
		when(kulturaRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> kulturaService.delete(1L));
	}
}
