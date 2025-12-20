package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ParcelaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.ParcelaService;

@WebMvcTest(controllers = ParcelaController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
@Import(GlobalExceptionHandler.class)
class ParcelaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParcelaService parcelaService;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private JwtService jwtService;

	@Test
	void testGetAllParcele() throws Exception {
		when(parcelaService.findAll()).thenReturn(List.of());

		mockMvc.perform(get("/api/parcele")).andExpect(status().isOk());
	}

	@Test
	void testGetByIdParcelu() throws Exception {
		ParcelaDto dto = new ParcelaDto();
		dto.setParcelaID(1L);
		dto.setNaziv("Parcela 1");
		dto.setLokacija("Pored reke");
		dto.setPovrsina(2.2);
		dto.setTipZemljista(1L);

		when(parcelaService.findById(1L)).thenReturn(dto);

		mockMvc.perform(get("/api/parcele/1")).andExpect(status().isOk()).andExpect(jsonPath("$.parcelaID").value(1))
				.andExpect(jsonPath("$.naziv").value("Parcela 1")).andExpect(jsonPath("$.lokacija").value("Pored reke"))
				.andExpect(jsonPath("$.povrsina").value(2.2)).andExpect(jsonPath("$.tipZemljista").value(1));
	}

	@Test
	void testGetByIdNotFoundParcelu() throws Exception {
		when(parcelaService.findById(10L)).thenReturn(null);

		mockMvc.perform(get("/api/parcele/10")).andExpect(status().isNotFound());
	}

	@Test
	void testCreateParcelu() throws Exception {
		ParcelaDto request = new ParcelaDto();
		request.setNaziv("Nova parcela");
		request.setLokacija("Reka");
		request.setPovrsina(5.0);
		request.setTipZemljista(1L);

		ParcelaDto created = new ParcelaDto();
		created.setParcelaID(10L);
		created.setNaziv("Nova parcela");
		created.setLokacija("Reka");
		created.setPovrsina(5.0);
		created.setTipZemljista(1L);

		when(parcelaService.save(any(ParcelaDto.class))).thenReturn(created);

		mockMvc.perform(post("/api/parcele").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.parcelaID").value(10)).andExpect(jsonPath("$.naziv").value("Nova parcela"))
				.andExpect(jsonPath("$.lokacija").value("Reka")).andExpect(jsonPath("$.povrsina").value(5.0))
				.andExpect(jsonPath("$.tipZemljista").value(1));
	}

	@Test
	void testCreateNotFoundTipZemljistaParcelu() throws Exception {
		ParcelaDto request = new ParcelaDto();
		request.setNaziv("Nova parcela");
		request.setLokacija("Brdo");
		request.setPovrsina(5.0);
		request.setTipZemljista(5L);

		when(parcelaService.save(any(ParcelaDto.class)))
				.thenThrow(new EntityNotFoundException("Tip zemljišta sa id=20 ne postoji"));

		mockMvc.perform(post("/api/parcele").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").exists());
	}

	@Test
	void testUpdateParcelu() throws Exception {
		ParcelaDto request = new ParcelaDto();
		request.setNaziv("Parcela za izmenu");
		request.setLokacija("Polje");
		request.setPovrsina(5.0);
		request.setTipZemljista(3L);

		ParcelaDto updated = new ParcelaDto();
		updated.setParcelaID(1L);
		updated.setNaziv("Parcela za izmenu");
		updated.setLokacija("Polje");
		updated.setPovrsina(20.0);
		updated.setTipZemljista(3L);

		when(parcelaService.update(any(ParcelaDto.class), eq(1L))).thenReturn(updated);

		mockMvc.perform(put("/api/parcele/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.parcelaID").value(1)).andExpect(jsonPath("$.naziv").value("Parcela za izmenu"))
				.andExpect(jsonPath("$.lokacija").value("Polje")).andExpect(jsonPath("$.povrsina").value(20.0))
				.andExpect(jsonPath("$.tipZemljista").value(3));
	}

	@Test
	void testUpdateNotFoundParcelu() throws Exception {
		ParcelaDto request = new ParcelaDto();
		request.setNaziv("Parcela");
		request.setLokacija("Brdo");
		request.setPovrsina(1.0);
		request.setTipZemljista(1L);

		when(parcelaService.update(any(ParcelaDto.class), eq(5L)))
				.thenThrow(new EntityNotFoundException("Parcela sa id=5 ne postoji"));

		mockMvc.perform(put("/api/parcele/5").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").exists());
	}

	@Test
	void testDeleteParcelu() throws Exception {
		doNothing().when(parcelaService).delete(1L);

		mockMvc.perform(delete("/api/parcele/1")).andExpect(status().isOk());

		verify(parcelaService).delete(1L);
	}

	@Test
	void testDeleteNotFoundParcelu() throws Exception {
		doNothing().when(parcelaService).delete(10L);

		org.mockito.Mockito.doThrow(new EntityNotFoundException("Parcela sa id=10 ne postoji")).when(parcelaService)
				.delete(99L);

		mockMvc.perform(delete("/api/parcele/99")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").exists());
	}
}
