package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AktivnostDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AktivnostService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;

@WebMvcTest(controllers = AktivnostController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
@Import(GlobalExceptionHandler.class)
class AktivnostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AktivnostService aktivnostService;

	@MockBean
	private JwtService jwtService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetAllAktivnosti() throws Exception {
		when(aktivnostService.findAll()).thenReturn(List.of());

		mockMvc.perform(get("/api/aktivnosti")).andExpect(status().isOk());
	}

	@Test
	void testGetByIdAktivnost() throws Exception {
		AktivnostDto dto = new AktivnostDto();
		dto.setAktivnostID(1L);
		dto.setNaziv("Setva");
		dto.setTipAktivnosti("SADNJA_I_SETVA");
		dto.setOpremaIDs(List.of(4L, 5L));

		when(aktivnostService.findById(1L)).thenReturn(dto);

		mockMvc.perform(get("/api/aktivnosti/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.aktivnostID").value(1)).andExpect(jsonPath("$.naziv").value("Setva"))
				.andExpect(jsonPath("$.tipAktivnosti").value("SADNJA_I_SETVA"))
				.andExpect(jsonPath("$.opremaIDs[0]").value(4)).andExpect(jsonPath("$.opremaIDs[1]").value(5));
	}

	@Test
	void testGetByIdNotFoundAktivnost() throws Exception {
		when(aktivnostService.findById(10L)).thenReturn(null);

		mockMvc.perform(get("/api/aktivnosti/10")).andExpect(status().isNotFound());
	}

	@Test
	void testCreateAktivnost() throws Exception {
		AktivnostDto request = new AktivnostDto();
		request.setNaziv("Prskanje");
		request.setTipAktivnosti("ZASTITA_USEVA");
		request.setOpremaIDs(List.of(5L));

		AktivnostDto created = new AktivnostDto();
		created.setAktivnostID(1L);
		created.setNaziv("Prskanje");
		created.setTipAktivnosti("ZASTITA_USEVA");
		created.setOpremaIDs(List.of(5L));

		when(aktivnostService.save(any(AktivnostDto.class))).thenReturn(created);

		mockMvc.perform(post("/api/aktivnosti").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.aktivnostID").value(1)).andExpect(jsonPath("$.naziv").value("Prskanje"))
				.andExpect(jsonPath("$.tipAktivnosti").value("ZASTITA_USEVA"))
				.andExpect(jsonPath("$.opremaIDs[0]").value(5));
	}

	@Test
	void testCreateBadRequestAktivnost() throws Exception {
		// naziv blank + opremaIDs prazno => pada @NotBlank i @Size(min=1)
		String badJson = """
				    {"naziv":"", "tipAktivnosti":"PRSKANJE", "opremaIDs":[]}
				""";

		mockMvc.perform(post("/api/aktivnosti").contentType(MediaType.APPLICATION_JSON).content(badJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateAktivnost() throws Exception {
		AktivnostDto request = new AktivnostDto();
		request.setNaziv("Setva update");
		request.setTipAktivnosti("SETVA");
		request.setOpremaIDs(List.of(1L));

		AktivnostDto updated = new AktivnostDto();
		updated.setAktivnostID(1L);
		updated.setNaziv("Setva update");
		updated.setTipAktivnosti("SETVA");
		updated.setOpremaIDs(List.of(1L));

		when(aktivnostService.update(eq(1L), any(AktivnostDto.class))).thenReturn(updated);

		mockMvc.perform(put("/api/aktivnosti/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.aktivnostID").value(1)).andExpect(jsonPath("$.naziv").value("Setva update"))
				.andExpect(jsonPath("$.tipAktivnosti").value("SETVA")).andExpect(jsonPath("$.opremaIDs[0]").value(1));
	}

	@Test
	void testUpdateNotFoundAktivnost() throws Exception {

		AktivnostDto request = new AktivnostDto();
		request.setNaziv("Setva");
		request.setTipAktivnosti("SETVA");
		request.setOpremaIDs(List.of(1L));

		when(aktivnostService.update(eq(99L), any(AktivnostDto.class)))
				.thenThrow(new EntityNotFoundException("Aktivnost sa id=99 ne postoji"));

		mockMvc.perform(put("/api/aktivnosti/99").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("Aktivnost sa id=99 ne postoji"));
	}

	@Test
	void testUpdateBadRequestAktivnost() throws Exception {
		String badJson = """
				    {"naziv":"", "tipAktivnosti":"SETVA", "opremaIDs":[]}
				""";

		mockMvc.perform(put("/api/aktivnosti/1").contentType(MediaType.APPLICATION_JSON).content(badJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testDeleteAktivnost() throws Exception {
		doNothing().when(aktivnostService).delete(1L);

		mockMvc.perform(delete("/api/aktivnosti/1")).andExpect(status().isOk());

		verify(aktivnostService).delete(1L);
	}

	@Test
	void testDeleteNotFoundAktivnost() throws Exception {

		doThrow(new EntityNotFoundException("Aktivnost sa id=99 ne postoji")).when(aktivnostService).delete(99L);

		mockMvc.perform(delete("/api/aktivnosti/99")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("Aktivnost sa id=99 ne postoji"));
	}

}
