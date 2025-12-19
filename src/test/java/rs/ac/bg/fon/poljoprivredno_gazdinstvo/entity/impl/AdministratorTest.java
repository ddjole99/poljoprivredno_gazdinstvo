package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AdministratorTest {

	private Administrator administrator;

	@BeforeEach
	void setUp() {
		administrator = new Administrator();
	}

	@AfterEach
	void tearDown() {
		administrator = null;
	}

	@Test
	void testSetAdministrator() {
		administrator.setAdministratorID(1L);
		assertEquals(1L, administrator.getAdministratorID());
	}

	@Test
	void testSetEmail() {
		administrator.setEmail("djordje@example.com");
		assertEquals("djordje@example.com", administrator.getEmail());
	}

	@Test
	void testSetUsername() {
		administrator.setUsername("djordje");
		assertEquals("djordje", administrator.getUsername());
	}

	@Test
	void testSetPasswordHash() {
		administrator.setPasswordHash("djordje1234");
		assertEquals("djordje1234", administrator.getPasswordHash());
	}

	@Test
	void testToString() {
		administrator.setEmail("djordje@test.com");
		administrator.setUsername("djordje");

		assertTrue(administrator.toString().contains("djordje@test.com"));
		assertTrue(administrator.toString().contains("djordje"));
	}

	@ParameterizedTest
	@CsvSource({ "admin@test.com, hash123, admin@test.com, hash123, true",
			"admin@test.com, hash123, admin2@test.com, hash123, false",
			"admin@test.com, hash123, admin@test.com, hash999, false",
			"admin@test.com, hash123, admin2@test.com, hash999, false" })
	void equalsTest(String email1, String password, String email2, String password2, boolean ocekivano) {

		administrator.setEmail(email1);
		administrator.setPasswordHash(password);

		Administrator a2 = new Administrator();
		a2.setEmail(email2);
		a2.setPasswordHash(password2);

		assertEquals(ocekivano, administrator.equals(a2));
	}
}
