package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorUpdateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.ChangePasswordRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AdministratorService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;

@WebMvcTest(controllers = AdministratorController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
class AdministratorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AdministratorService administratorService;

	@MockBean
	private JwtService jwtService;

	@Test
	void testGetAllAdministratori() throws Exception {
		when(administratorService.findAll()).thenReturn(List.of());

		mockMvc.perform(get("/api/administratori")).andExpect(status().isOk());
	}

	@Test
	void testGetByIdAdministrator() throws Exception {
		AdministratorDto dto = new AdministratorDto();
		dto.setAdministratorID(1L);
		dto.setUsername("djordje");
		dto.setEmail("djordje@test.com");

		when(administratorService.findById(1L)).thenReturn(dto);

		mockMvc.perform(get("/api/administratori/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.administratorID").value(1)).andExpect(jsonPath("$.username").value("djordje"))
				.andExpect(jsonPath("$.email").value("djordje@test.com"));
	}

	@Test
	void testGetByIdNotFoundAdministrator() throws Exception {
		when(administratorService.findById(10L)).thenReturn(null);

		mockMvc.perform(get("/api/administratori/10")).andExpect(status().isNotFound());
	}

	@Test
	void testUpdateAdministrator() throws Exception {
		AdministratorUpdateRequest request = new AdministratorUpdateRequest("newAdmin", "new@test.com");

		AdministratorDto updated = new AdministratorDto();
		updated.setAdministratorID(1L);
		updated.setUsername("newAdmin");
		updated.setEmail("new@test.com");

		when(administratorService.update(any(AdministratorUpdateRequest.class), eq(1L))).thenReturn(updated);

		mockMvc.perform(put("/api/administratori/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("newAdmin"))
				.andExpect(jsonPath("$.email").value("new@test.com"));
	}

	@Test
	void testDeleteAdministrator() throws Exception {
		doNothing().when(administratorService).delete(1L);

		mockMvc.perform(delete("/api/administratori/1")).andExpect(status().isOk());
	}

	@Test
	void testChangePasswordAdministrator() throws Exception {
		ChangePasswordRequest request = new ChangePasswordRequest("oldPassword", "newPassword123");

		doNothing().when(administratorService).changePassword(eq(1L), any(ChangePasswordRequest.class));

		mockMvc.perform(post("/api/administratori/1/change-password").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
	}
}
