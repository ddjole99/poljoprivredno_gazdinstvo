package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KulturaTest {

    private Kultura kultura;

    @BeforeEach
    void setUp() {
        kultura = new Kultura();
    }

    @AfterEach
    void tearDown() {
        kultura = null;
    }

    @Test
    void testSetKultura() {
        kultura.setKulturaID(1L);
        assertEquals(1L, kultura.getKulturaID());
    }

    @Test
    void testSetNaziv() {
        kultura.setNaziv("Kukuruz");
        assertEquals("Kukuruz", kultura.getNaziv());
    }


    @Test
    void testSetSorta() {
        kultura.setSorta("AS180");
        assertEquals("AS180", kultura.getSorta());
    }

    @Test
    void testToString() {
        kultura.setNaziv("Kukuruz");
        kultura.setSorta("AS180");


        assertTrue(kultura.toString().contains("Kukuruz"));
        assertTrue(kultura.toString().contains("AS180"));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false",
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        kultura.setKulturaID(id1);

        Kultura k2 = new Kultura();
        k2.setKulturaID(id2);

        assertEquals(ocekivano, kultura.equals(k2));
    }


}
