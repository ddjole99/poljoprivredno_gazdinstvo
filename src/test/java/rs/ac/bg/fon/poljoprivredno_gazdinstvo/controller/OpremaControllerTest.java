package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.OpremaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.OpremaService;

@WebMvcTest(controllers = OpremaController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
class OpremaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OpremaService opremaService;

	@MockBean
	private JwtService jwtService;

	@Test
	void testGetAllOprema() throws Exception {
		when(opremaService.findAll()).thenReturn(List.of());

		mockMvc.perform(get("/api/oprema").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testGetByIdOpremu() throws Exception {
		OpremaDto dto = new OpremaDto();
		dto.setOpremaID(1L);
		dto.setNaziv("Traktor");
		dto.setTipOpreme("Mehanizacija");

		when(opremaService.findById(1L)).thenReturn(dto);

		mockMvc.perform(get("/api/oprema/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.opremaID").value(1)).andExpect(jsonPath("$.naziv").value("Traktor"))
				.andExpect(jsonPath("$.tipOpreme").value("Mehanizacija"));
	}

	@Test
	void testGetByIdNotFoundOpremu() throws Exception {
		when(opremaService.findById(2L)).thenReturn(null);

		mockMvc.perform(get("/api/oprema/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}
