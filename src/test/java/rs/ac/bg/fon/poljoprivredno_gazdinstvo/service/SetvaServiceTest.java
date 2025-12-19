package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.StavkaSetveDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.*;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.SetvaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.*;

@ExtendWith(MockitoExtension.class)
class SetvaServiceTest {

	@Mock
	private SetvaRepository setvaRepository;
	@Mock
	private SetvaMapper setvaMapper;
	@Mock
	private AdministratorRepository administratorRepository;
	@Mock
	private ParcelaRepository parcelaRepository;
	@Mock
	private KulturaRepository kulturaRepository;
	@Mock
	private AktivnostRepository aktivnostRepository;

	@InjectMocks
	private SetvaService setvaService;

	@Test
	void testFindAllSetve() {
		Setva s1 = new Setva(1L);
		Setva s2 = new Setva(2L);

		SetvaDto d1 = new SetvaDto(1L);
		SetvaDto d2 = new SetvaDto(2L);

		when(setvaRepository.findAll()).thenReturn(List.of(s1, s2));
		when(setvaMapper.toDto(s1)).thenReturn(d1);
		when(setvaMapper.toDto(s2)).thenReturn(d2);

		List<SetvaDto> result = setvaService.findAll();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void testFindByIdSetvu() {
		Setva entity = new Setva(1L);
		SetvaDto dto = new SetvaDto(1L);

		when(setvaRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(setvaMapper.toDto(entity)).thenReturn(dto);

		SetvaDto result = setvaService.findById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getSetvaID());
	}

	@Test
	void testFindByIdNotFoundSetvu() {
		when(setvaRepository.findById(1L)).thenReturn(Optional.empty());

		SetvaDto result = setvaService.findById(1L);

		assertNull(result);
	}

	@Test
	void testSaveSetvu() {
		SetvaDto dto = new SetvaDto();
		dto.setAdministratorID(1L);
		dto.setParcelaID(2L);
		dto.setKulturaID(3L);
		dto.setDatumPocetka(LocalDate.now());
		dto.setDatumZavrsetka(LocalDate.now());
		dto.setStatus(Status.PLANIRANA);

		Setva entity = new Setva();
		entity.setStavkeSetve(new ArrayList<>());

		Administrator admin = new Administrator(1L);
		Parcela parcela = new Parcela(2L);
		Kultura kultura = new Kultura(3L);

		when(administratorRepository.findById(1L)).thenReturn(Optional.of(admin));
		when(parcelaRepository.findById(2L)).thenReturn(Optional.of(parcela));
		when(kulturaRepository.findById(3L)).thenReturn(Optional.of(kultura));
		when(setvaMapper.toEntity(dto)).thenReturn(entity);

		Setva saved = new Setva(10L);
		when(setvaRepository.save(entity)).thenReturn(saved);
		when(setvaMapper.toDto(saved)).thenReturn(new SetvaDto(10L));

		SetvaDto result = setvaService.save(dto);

		assertNotNull(result);
		assertEquals(10L, result.getSetvaID());
		assertEquals(admin, entity.getAdministrator());
		assertEquals(parcela, entity.getParcela());
		assertEquals(kultura, entity.getKultura());
	}

	@Test
	void testSaveAdministratorNotFound() {
		SetvaDto dto = new SetvaDto();
		dto.setAdministratorID(1L);

		when(administratorRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> setvaService.save(dto));
	}

	@Test
	void testUpdateSetvu() {
		Long id = 1L;

		Setva existing = new Setva(id);
		existing.setStavkeSetve(new ArrayList<>());

		SetvaDto dto = new SetvaDto();
		dto.setAdministratorID(1L);
		dto.setParcelaID(2L);
		dto.setKulturaID(3L);
		dto.setDatumPocetka(LocalDate.now());
		dto.setDatumZavrsetka(LocalDate.now());
		dto.setStatus(Status.U_TOKU);

		StavkaSetveDto sDto = new StavkaSetveDto();
		sDto.setId(5L);
		sDto.setAktivnostID(10L);
		sDto.setCena(1000.0);
		sDto.setDatum(LocalDate.now());

		dto.setStavkeSetve(List.of(sDto));

		when(setvaRepository.findById(id)).thenReturn(Optional.of(existing));
		when(administratorRepository.findById(1L)).thenReturn(Optional.of(new Administrator(1L)));
		when(parcelaRepository.findById(2L)).thenReturn(Optional.of(new Parcela(2L)));
		when(kulturaRepository.findById(3L)).thenReturn(Optional.of(new Kultura(3L)));
		when(aktivnostRepository.findById(10L)).thenReturn(Optional.of(new Aktivnost(10L)));

		when(setvaRepository.save(existing)).thenReturn(existing);
		when(setvaMapper.toDto(existing)).thenReturn(new SetvaDto(id));

		SetvaDto result = setvaService.update(dto, id);

		assertNotNull(result);
		assertEquals(id, result.getSetvaID());
		assertEquals(1, existing.getStavkeSetve().size());
	}

	@Test
	void testUpdateSetvaNotFound() {
		when(setvaRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> setvaService.update(new SetvaDto(), 1L));
	}

	@Test
	void testDeleteSetvu() {
		Setva s = new Setva(1L);
		when(setvaRepository.findById(1L)).thenReturn(Optional.of(s));

		setvaService.delete(1L);

		verify(setvaRepository).delete(s);
	}

	@Test
	void testDeleteNotFoundSetvu() {
		when(setvaRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> setvaService.delete(1L));
	}
}
