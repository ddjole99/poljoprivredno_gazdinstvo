package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.SetvaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Status;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.SetvaService;

@WebMvcTest(
    controllers = SetvaController.class,
    excludeAutoConfiguration = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class }
)
class SetvaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SetvaService setvaService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @MockBean
    private JwtService jwtService;
    
    @Test
    void testGetAllSetve() throws Exception {
        when(setvaService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/setve"))
               .andExpect(status().isOk());
    }

    @Test
    void testGetByIdSetvu() throws Exception {
        SetvaDto dto = validSetvaDto();
        dto.setSetvaID(1L);

        when(setvaService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/setve/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.setvaID").value(1));
    }

    @Test
    void testGetByIdNotFoundSetvu() throws Exception {
        when(setvaService.findById(10L)).thenReturn(null);

        mockMvc.perform(get("/api/setve/10"))
               .andExpect(status().isNotFound());
    }
    
    @Test
    void testCreateSetvu() throws Exception {
        SetvaDto request = validSetvaDto();

        SetvaDto created = validSetvaDto();
        created.setSetvaID(1L);

        when(setvaService.save(any(SetvaDto.class))).thenReturn(created);

        mockMvc.perform(post("/api/setve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.setvaID").value(1));
    }

    @Test
    void testCreateBadRequestSetvu() throws Exception {
        String jsonBody = "{}";

        mockMvc.perform(post("/api/setve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
               .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateSetvu() throws Exception {
        SetvaDto request = validSetvaDto();

        SetvaDto updated = validSetvaDto();
        updated.setSetvaID(1L);

        when(setvaService.update(any(SetvaDto.class), eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/api/setve/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.setvaID").value(1));
    }

    @Test
    void testUpdateNotFoundSetvu() throws Exception {
        SetvaDto request = validSetvaDto();

        when(setvaService.update(any(SetvaDto.class), eq(5L))).thenReturn(null);

        mockMvc.perform(put("/api/setve/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteSetvu() throws Exception {
        doNothing().when(setvaService).delete(1L);

        mockMvc.perform(delete("/api/setve/1"))
               .andExpect(status().isOk());
    }

    private SetvaDto validSetvaDto() {
        SetvaDto dto = new SetvaDto();
        dto.setAdministratorID(1L);
        dto.setKulturaID(2L);
        dto.setParcelaID(3L);
        dto.setDatumPocetka(LocalDate.now());
        dto.setDatumZavrsetka(LocalDate.now().plusDays(60));
        dto.setStatus(Status.U_TOKU);
        return dto;
    }
}
