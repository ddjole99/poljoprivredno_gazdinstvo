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

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AktivnostDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Aktivnost;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipAktivnosti;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.AktivnostMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.AktivnostRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository;

@ExtendWith(MockitoExtension.class)
class AktivnostServiceTest {

	@Mock
	private AktivnostRepository aktivnostRepository;

	@Mock
	private AktivnostMapper aktivnostMapper;

	@Mock
	private OpremaRepository opremaRepository;

	@InjectMocks
	private AktivnostService aktivnostService;

	@Test
	void testFindAll() {
		Aktivnost a1 = new Aktivnost(1L);
		Aktivnost a2 = new Aktivnost(2L);

		AktivnostDto d1 = new AktivnostDto();
		d1.setAktivnostID(1L);
		AktivnostDto d2 = new AktivnostDto();
		d2.setAktivnostID(2L);

		when(aktivnostRepository.findAll()).thenReturn(List.of(a1, a2));
		when(aktivnostMapper.toDto(a1)).thenReturn(d1);
		when(aktivnostMapper.toDto(a2)).thenReturn(d2);

		List<AktivnostDto> result = aktivnostService.findAll();

		assertEquals(2, result.size());
		verify(aktivnostRepository).findAll();
		verify(aktivnostMapper).toDto(a1);
		verify(aktivnostMapper).toDto(a2);
	}

	@Test
	void testFindByIdAktivnost() {
		Long id = 1L;
		Aktivnost entity = new Aktivnost(id);

		AktivnostDto dto = new AktivnostDto();
		dto.setAktivnostID(id);

		when(aktivnostRepository.findById(id)).thenReturn(Optional.of(entity));
		when(aktivnostMapper.toDto(entity)).thenReturn(dto);

		AktivnostDto result = aktivnostService.findById(id);

		assertEquals(id, result.getAktivnostID());
		verify(aktivnostRepository).findById(id);
		verify(aktivnostMapper).toDto(entity);
	}

	@Test
	void testFindByIdNotFoundAktivnost() {
		when(aktivnostRepository.findById(99L)).thenReturn(Optional.empty());

		AktivnostDto result = aktivnostService.findById(99L);

		assertNull(result);
		verify(aktivnostRepository).findById(99L);
		verifyNoInteractions(aktivnostMapper);
	}

	@Test
	void testSaveAktivnost() {
		AktivnostDto input = new AktivnostDto();
		input.setNaziv("Oranje");
		input.setTipAktivnosti("TESKA_OBRADA");
		input.setOpremaIDs(List.of(1L, 2L));

		Aktivnost entity = new Aktivnost();
		entity.setNaziv("Oranje");

		Oprema o1 = new Oprema(1L);
		Oprema o2 = new Oprema(2L);

		Aktivnost saved = new Aktivnost(10L);
		saved.setNaziv("Oranje");
		saved.setOprema(List.of(o1, o2));

		AktivnostDto output = new AktivnostDto();
		output.setAktivnostID(10L);

		when(aktivnostMapper.toEntity(input)).thenReturn(entity);
		when(opremaRepository.findAllById(input.getOpremaIDs())).thenReturn(List.of(o1, o2));
		when(aktivnostRepository.save(entity)).thenReturn(saved);
		when(aktivnostMapper.toDto(saved)).thenReturn(output);

		AktivnostDto result = aktivnostService.save(input);

		assertNotNull(result);
		assertEquals(10L, result.getAktivnostID());

		assertNotNull(entity.getOprema());
		assertEquals(2, entity.getOprema().size());

		verify(aktivnostMapper).toEntity(input);
		verify(opremaRepository).findAllById(input.getOpremaIDs());
		verify(aktivnostRepository).save(entity);
		verify(aktivnostMapper).toDto(saved);
	}

	@Test
	void testSaveOpremaNotFound() {
		AktivnostDto input = new AktivnostDto();
		input.setNaziv("Oranje");
		input.setOpremaIDs(List.of(1L, 2L));

		Aktivnost entity = new Aktivnost();
		when(aktivnostMapper.toEntity(input)).thenReturn(entity);

		// repo vrati samo jednu opremu, a tražene su dve -> treba exception
		when(opremaRepository.findAllById(input.getOpremaIDs())).thenReturn(List.of(new Oprema(1L)));

		assertThrows(EntityNotFoundException.class, () -> aktivnostService.save(input));

		verify(aktivnostMapper).toEntity(input);
		verify(opremaRepository).findAllById(input.getOpremaIDs());
		verifyNoInteractions(aktivnostRepository);
	}

	@Test
	void testUpdateAktivnost() {
		Long id = 1L;

		Aktivnost existing = new Aktivnost(id);
		existing.setNaziv("Stara");
		existing.setTipAktivnosti(TipAktivnosti.NAVODNJAVANJE);

		AktivnostDto input = new AktivnostDto();
		input.setNaziv("Nova");
		input.setTipAktivnosti("NAVODNJAVANJE");
		input.setOpremaIDs(List.of(1L));

		Oprema o1 = new Oprema(1L);

		Aktivnost saved = new Aktivnost(id);
		saved.setNaziv("NAVODNJAVANJE");
		saved.setTipAktivnosti(TipAktivnosti.NAVODNJAVANJE);
		saved.setOprema(List.of(o1));

		AktivnostDto output = new AktivnostDto();
		output.setAktivnostID(id);

		when(aktivnostRepository.findById(id)).thenReturn(Optional.of(existing));
		when(opremaRepository.findAllById(input.getOpremaIDs())).thenReturn(List.of(o1));
		when(aktivnostRepository.save(existing)).thenReturn(saved);
		when(aktivnostMapper.toDto(saved)).thenReturn(output);

		AktivnostDto result = aktivnostService.update(id, input);

		assertNotNull(result);
		assertEquals(id, result.getAktivnostID());
		assertEquals("Nova", existing.getNaziv());
		assertEquals(TipAktivnosti.NAVODNJAVANJE, existing.getTipAktivnosti());
		assertEquals(1, existing.getOprema().size());

		verify(aktivnostRepository).findById(id);
		verify(opremaRepository).findAllById(input.getOpremaIDs());
		verify(aktivnostRepository).save(existing);
		verify(aktivnostMapper).toDto(saved);
	}

	@Test
	void testUpdateNotFoundAktivnost() {
		when(aktivnostRepository.findById(1L)).thenReturn(Optional.empty());

		AktivnostDto input = new AktivnostDto();
		input.setNaziv("Nova");
		input.setTipAktivnosti("NAVODNJAVANJE");

		assertThrows(EntityNotFoundException.class, () -> aktivnostService.update(1L, input));

		verify(aktivnostRepository).findById(1L);
		verifyNoInteractions(opremaRepository);
		verifyNoInteractions(aktivnostMapper);
	}

	@Test
	void testUpdateOpremaNotFound() {
		Long id = 1L;

		Aktivnost existing = new Aktivnost(id);

		AktivnostDto input = new AktivnostDto();
		input.setNaziv("Nova");
		input.setTipAktivnosti("NAVODNJAVANJE");
		input.setOpremaIDs(List.of(1L, 2L));

		when(aktivnostRepository.findById(id)).thenReturn(Optional.of(existing));
		// vrati samo jednu opremu umesto dve
		when(opremaRepository.findAllById(input.getOpremaIDs())).thenReturn(List.of(new Oprema(1L)));

		assertThrows(EntityNotFoundException.class, () -> aktivnostService.update(id, input));

		verify(aktivnostRepository).findById(id);
		verify(opremaRepository).findAllById(input.getOpremaIDs());
		verifyNoMoreInteractions(aktivnostRepository); // save se ne sme pozvati
	}

	@Test
	void testUpdateOpremaIdsNull() {
		Long id = 1L;

		Aktivnost existing = new Aktivnost(id);

		AktivnostDto input = new AktivnostDto();
		input.setNaziv("Nova");
		input.setTipAktivnosti("NAVODNJAVANJE");
		input.setOpremaIDs(null); // bitno: grana ids == null

		Aktivnost saved = new Aktivnost(id);

		AktivnostDto output = new AktivnostDto();
		output.setAktivnostID(id);

		when(aktivnostRepository.findById(id)).thenReturn(Optional.of(existing));
		when(aktivnostRepository.save(existing)).thenReturn(saved);
		when(aktivnostMapper.toDto(saved)).thenReturn(output);

		AktivnostDto result = aktivnostService.update(id, input);

		assertNotNull(result);
		verify(aktivnostRepository).findById(id);
		verify(aktivnostRepository).save(existing);
		verifyNoInteractions(opremaRepository);
	}

	@Test
	void testDeleteAktivnost() {
		Long id = 1L;
		Aktivnost entity = new Aktivnost(id);

		when(aktivnostRepository.findById(id)).thenReturn(Optional.of(entity));

		aktivnostService.delete(id);

		verify(aktivnostRepository).delete(entity);
	}

	@Test
	void testDeleteNotFoundAktivnost() {
		when(aktivnostRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> aktivnostService.delete(1L));

		verify(aktivnostRepository).findById(1L);
		verify(aktivnostRepository, never()).delete(any());
	}
}
