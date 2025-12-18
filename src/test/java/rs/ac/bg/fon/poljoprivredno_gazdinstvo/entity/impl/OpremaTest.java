package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OpremaTest {

    private Oprema oprema;

    @BeforeEach
    void setUp() {
        oprema = new Oprema();
    }

    @AfterEach
    void tearDown() {
        oprema = null;
    }

    @Test
    void testSetOprema() {
        oprema.setOpremaID(1L);
        assertEquals(1L, oprema.getOpremaID());
    }

    @Test
    void testSetNaziv() {
        oprema.setNaziv("Traktor");
        assertEquals("Traktor", oprema.getNaziv());
    }

    @Test
    void testSetTipOpreme() {
        oprema.setTipOpreme("Mehanizacija");
        assertEquals("Mehanizacija", oprema.getTipOpreme());
    }

    @Test
    void testSetAktivnosti() {
        List<Aktivnost> lista = new ArrayList<>();
        oprema.setAktivnosti(lista);

        assertNotNull(oprema.getAktivnosti());
        assertSame(lista, oprema.getAktivnosti());
        assertEquals(0, oprema.getAktivnosti().size());
    }

    @ParameterizedTest
    @CsvSource({
        "Traktor, Mehanizacija",
        "Prskalica, Zastita",
        "Sejalica, Setva"
    })
    void testToString(String naziv, String tipOpreme) {
        oprema.setNaziv(naziv);
        oprema.setTipOpreme(tipOpreme);

        String result = oprema.toString();

        assertTrue(result.contains(naziv));
        assertTrue(result.contains(tipOpreme));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false"
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        oprema.setOpremaID(id1);

        Oprema o2 = new Oprema();
        o2.setOpremaID(id2);

        assertEquals(ocekivano, oprema.equals(o2));
    }
}
