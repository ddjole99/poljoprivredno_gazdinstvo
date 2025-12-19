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

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Parcela;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.TipZemljista;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.ParcelaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.ParcelaRepository;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.TipZemljistaRepository;

@ExtendWith(MockitoExtension.class)
class ParcelaServiceTest {

	@Mock
	private ParcelaRepository parcelaRepository;

	@Mock
	private TipZemljistaRepository tipZemljistaRepository;

	@Mock
	private ParcelaMapper parcelaMapper;

	@InjectMocks
	private ParcelaService parcelaService;

	@Test
	void testFindAllParcele() {
		Parcela p1 = new Parcela(1L);
		Parcela p2 = new Parcela(2L);

		ParcelaDto d1 = new ParcelaDto();
		d1.setParcelaID(1L);
		ParcelaDto d2 = new ParcelaDto();
		d2.setParcelaID(2L);

		when(parcelaRepository.findAll()).thenReturn(List.of(p1, p2));
		when(parcelaMapper.toDto(p1)).thenReturn(d1);
		when(parcelaMapper.toDto(p2)).thenReturn(d2);

		List<ParcelaDto> result = parcelaService.findAll();

		assertNotNull(result);
		assertEquals(2, result.size());

		verify(parcelaRepository).findAll();
		verify(parcelaMapper).toDto(p1);
		verify(parcelaMapper).toDto(p2);
	}

	@Test
	void testFindByIdParcelu() {
		Long id = 1L;

		Parcela parcela = new Parcela(id);
		ParcelaDto dto = new ParcelaDto();
		dto.setParcelaID(id);

		when(parcelaRepository.findById(id)).thenReturn(Optional.of(parcela));
		when(parcelaMapper.toDto(parcela)).thenReturn(dto);

		ParcelaDto result = parcelaService.findById(id);

		assertNotNull(result);
		assertEquals(id, result.getParcelaID());

		verify(parcelaRepository).findById(id);
		verify(parcelaMapper).toDto(parcela);
	}

	@Test
	void testFindByIdNotFound() {
		when(parcelaRepository.findById(99L)).thenReturn(Optional.empty());

		ParcelaDto result = parcelaService.findById(99L);

		assertNull(result);
		verify(parcelaRepository).findById(99L);
		verifyNoInteractions(parcelaMapper);
	}

	@Test
	void testSaveParcelu() {
		ParcelaDto input = new ParcelaDto();
		input.setNaziv("Njiva 1");
		input.setLokacija("Banat");
		input.setPovrsina(10.5);
		input.setTipZemljista(1L);

		TipZemljista tz = new TipZemljista(1L);

		Parcela entity = new Parcela();
		Parcela saved = new Parcela(5L);

		ParcelaDto output = new ParcelaDto();
		output.setParcelaID(5L);

		when(tipZemljistaRepository.findById(1L)).thenReturn(Optional.of(tz));
		when(parcelaMapper.toEntity(input)).thenReturn(entity);
		when(parcelaRepository.save(entity)).thenReturn(saved);
		when(parcelaMapper.toDto(saved)).thenReturn(output);

		ParcelaDto result = parcelaService.save(input);

		assertNotNull(result);
		assertEquals(5L, result.getParcelaID());
		assertEquals(tz, entity.getTipZemljista());

		verify(tipZemljistaRepository).findById(1L);
		verify(parcelaMapper).toEntity(input);
		verify(parcelaRepository).save(entity);
		verify(parcelaMapper).toDto(saved);
	}

	@Test
	void testSaveTipZemljistaNotFound() {
		ParcelaDto input = new ParcelaDto();
		input.setTipZemljista(1L);

		when(tipZemljistaRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> parcelaService.save(input));

		verify(tipZemljistaRepository).findById(1L);
		verifyNoInteractions(parcelaRepository);
		verifyNoInteractions(parcelaMapper);
	}

	@Test
	void testUpdateParcelu() {
		Long id = 1L;

		Parcela parcela = new Parcela(id);

		ParcelaDto input = new ParcelaDto();
		input.setNaziv("Nova parcela");
		input.setLokacija("Srem");
		input.setPovrsina(20.0);
		input.setTipZemljista(2L);

		TipZemljista tz = new TipZemljista(2L);

		ParcelaDto output = new ParcelaDto();
		output.setParcelaID(id);

		when(parcelaRepository.findById(id)).thenReturn(Optional.of(parcela));
		when(tipZemljistaRepository.findById(2L)).thenReturn(Optional.of(tz));
		when(parcelaMapper.toDto(parcela)).thenReturn(output);

		ParcelaDto result = parcelaService.update(input, id);

		assertNotNull(result);
		assertEquals("Nova parcela", parcela.getNaziv());
		assertEquals("Srem", parcela.getLokacija());
		assertEquals(20.0, parcela.getPovrsina());
		assertEquals(tz, parcela.getTipZemljista());

		verify(parcelaRepository).findById(id);
		verify(tipZemljistaRepository).findById(2L);
		verify(parcelaRepository).save(parcela);
		verify(parcelaMapper).toDto(parcela);
	}

	@Test
	void testUpdateParcelaNotFound() {
		when(parcelaRepository.findById(1L)).thenReturn(Optional.empty());

		ParcelaDto input = new ParcelaDto();

		assertThrows(EntityNotFoundException.class, () -> parcelaService.update(input, 1L));

		verify(parcelaRepository).findById(1L);
		verifyNoInteractions(tipZemljistaRepository);
	}

	@Test
	void testUpdateTipZemljistaNotFound() {
		Long id = 1L;

		Parcela parcela = new Parcela(id);

		ParcelaDto input = new ParcelaDto();
		input.setTipZemljista(5L);

		when(parcelaRepository.findById(id)).thenReturn(Optional.of(parcela));
		when(tipZemljistaRepository.findById(5L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> parcelaService.update(input, id));

		verify(parcelaRepository).findById(id);
		verify(tipZemljistaRepository).findById(5L);
		verify(parcelaRepository, never()).save(any());
	}

	@Test
	void testDeleteParcelu() {
		Parcela parcela = new Parcela(1L);

		when(parcelaRepository.findById(1L)).thenReturn(Optional.of(parcela));

		parcelaService.delete(1L);

		verify(parcelaRepository).delete(parcela);
	}

	@Test
	void testDeleteNotFoundParcelu() {
		when(parcelaRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> parcelaService.delete(1L));

		verify(parcelaRepository).findById(1L);
		verify(parcelaRepository, never()).delete(any());
	}
}
