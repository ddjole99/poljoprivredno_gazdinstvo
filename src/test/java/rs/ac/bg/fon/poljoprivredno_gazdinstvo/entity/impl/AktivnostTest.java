package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AktivnostTest {

    private Aktivnost aktivnost;

    @BeforeEach
    void setUp() {
        aktivnost = new Aktivnost();
    }

    @AfterEach
    void tearDown() {
        aktivnost = null;
    }

    @Test
    void testSetAktivnost() {
        aktivnost.setAktivnostID(1L);
        assertEquals(1L, aktivnost.getAktivnostID());
    }

    @Test
    void testSetNaziv() {
        aktivnost.setNaziv("Oranje");
        assertEquals("Oranje", aktivnost.getNaziv());
    }

    @Test
    void testSetTipAktivnosti() {
        aktivnost.setTipAktivnosti(TipAktivnosti.TESKA_OBRADA);
        assertEquals(TipAktivnosti.TESKA_OBRADA, aktivnost.getTipAktivnosti());
    }

    @Test
    void testSetOprema() {
        List<Oprema> lista = new ArrayList<>();
        lista.add(new Oprema(1L));

        aktivnost.setOprema(lista);

        assertNotNull(aktivnost.getOprema());
        assertSame(lista, aktivnost.getOprema());
        assertEquals(1, aktivnost.getOprema().size());
    }

    @Test
    void testToString() {
        aktivnost.setNaziv("Oranje");

        assertTrue(aktivnost.toString().contains("Oranje"));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false"
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        aktivnost.setAktivnostID(id1);

        Aktivnost a2 = new Aktivnost();
        a2.setAktivnostID(id2);

        assertEquals(ocekivano, aktivnost.equals(a2));
    }
}
