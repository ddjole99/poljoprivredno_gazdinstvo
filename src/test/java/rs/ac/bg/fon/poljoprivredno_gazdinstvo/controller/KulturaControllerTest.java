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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.KulturaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.KulturaService;

@WebMvcTest(controllers = KulturaController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
class KulturaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private KulturaService kulturaService;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private JwtService jwtService;

	@Test
	void testGetAllKulture() throws Exception {
		when(kulturaService.findAll()).thenReturn(List.of());

		mockMvc.perform(get("/api/kulture")).andExpect(status().isOk());
	}

	@Test
	void testGetByIdKulturu() throws Exception {
		KulturaDto dto = new KulturaDto();
		dto.setKulturaID(1L);
		dto.setNaziv("Psenica");

		when(kulturaService.findById(1L)).thenReturn(dto);

		mockMvc.perform(get("/api/kulture/1")).andExpect(status().isOk()).andExpect(jsonPath("$.kulturaID").value(1))
				.andExpect(jsonPath("$.naziv").value("Psenica"));
	}

	@Test
	void testGetByIdNotFoundKulturu() throws Exception {
		when(kulturaService.findById(99L)).thenReturn(null);

		mockMvc.perform(get("/api/kulture/99")).andExpect(status().isNotFound());
	}

	@Test
	void testCreateKulturu() throws Exception {
		KulturaDto request = new KulturaDto();
		request.setNaziv("Kukuruz");
		request.setSorta("AS180");

		KulturaDto created = new KulturaDto();
		created.setKulturaID(10L);
		created.setNaziv("Kukuruz");
		created.setSorta("AS180");

		when(kulturaService.save(any(KulturaDto.class))).thenReturn(created);

		mockMvc.perform(post("/api/kulture").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.kulturaID").value(10)).andExpect(jsonPath("$.naziv").value("Kukuruz"))
				.andExpect(jsonPath("$.sorta").value("AS180"));
	}

	@Test
	void testUpdateKulturu() throws Exception {
		KulturaDto request = new KulturaDto();
		request.setNaziv("Psenica");
		request.setSorta("Axereal");

		KulturaDto updated = new KulturaDto();
		updated.setKulturaID(1L);
		updated.setNaziv("Psenica");
		updated.setSorta("Axereal");

		when(kulturaService.update(eq(1L), any(KulturaDto.class))).thenReturn(updated);

		mockMvc.perform(put("/api/kulture/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.naziv").value("Psenica")).andExpect(jsonPath("$.sorta").value("Axereal"));
	}

	@Test
	void testUpdateNotFoundKulturu() throws Exception {
		when(kulturaService.update(eq(5L), any(KulturaDto.class))).thenReturn(null);

		String jsonRequest = """
				{"naziv":"Psenica","sorta":"Axereal"}
				  """;
		mockMvc.perform(put("/api/kulture/5").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteKulturu() throws Exception {
		doNothing().when(kulturaService).delete(1L);

		mockMvc.perform(delete("/api/kulture/1")).andExpect(status().isOk());

		verify(kulturaService).delete(1L);
	}
}
