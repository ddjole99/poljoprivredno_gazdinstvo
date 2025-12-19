package rs.ac.bg.fon.poljoprivredno_gazdinstvo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorCreateRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.AdministratorLoginRequest;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.AdministratorService;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;

@WebMvcTest(
    controllers = AuthController.class,
    excludeAutoConfiguration = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class }
)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdministratorService administratorService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @Test
    void testRegister() throws Exception {
        AdministratorCreateRequest req = new AdministratorCreateRequest();
        req.setUsername("djordje");
        req.setEmail("djordje@test.com");
        req.setPassword("djordje1234");

        AdministratorDto created = new AdministratorDto();
        created.setAdministratorID(1L);
        created.setUsername("djordje");
        created.setEmail("djordje@test.com");

        when(administratorService.create(any(AdministratorCreateRequest.class))).thenReturn(created);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location",endsWith("/api/administratori/1")))
            .andExpect(jsonPath("$.administratorID").value(1))
            .andExpect(jsonPath("$.username").value("djordje"))
            .andExpect(jsonPath("$.email").value("djordje@test.com"));
    }

    @Test
    void testRegisterBadRequest() throws Exception {
        String jsonBody = """
                {
                  "username": "",
                  "email": "email",
                  "password": "123"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testLogin() throws Exception {
        AdministratorLoginRequest req = new AdministratorLoginRequest();
        req.setEmail("djordje@test.com");
        req.setPassword("djordje1234");

        Authentication auth = Mockito.mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        when(jwtService.generateToken(eq("djordje@test.com"))).thenReturn("TOKEN123");

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value("TOKEN123"));
    }

    @Test
    void testLoginBadRequest() throws Exception {
        String badJson = """
                {
                  "email": "email",
                  "password": "123"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(badJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    void validate_trueTest() throws Exception {
        when(jwtService.validateToken(eq("ABC.DEF.GHI"))).thenReturn(true);

        mockMvc.perform(post("/api/auth/validate")
                .header("Authorization", "Bearer ABC.DEF.GHI"))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    @Test
    void testValidateBadRequest() throws Exception {
        mockMvc.perform(post("/api/auth/validate"))
            .andExpect(status().isBadRequest());
    }
}
