package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StavkaSetveTest {

    private StavkaSetve stavka;

    @BeforeEach
    void setUp() {
        stavka = new StavkaSetve();
    }

    @AfterEach
    void tearDown() {
        stavka = null;
    }

    @Test
    void testSetId() {
        stavka.setId(1L);
        assertEquals(1L, stavka.getId());
    }

    @Test
    void testSetCena() {
        stavka.setCena(2500.0);
        assertEquals(2500.0, stavka.getCena());
    }

    @Test
    void testSetDatum() {
        LocalDate datum = LocalDate.of(2025, 3, 15);
        stavka.setDatum(datum);

        assertEquals(datum, stavka.getDatum());
    }

    @Test
    void testSetSetva() {
        Setva s = new Setva();
        s.setSetvaID(1L);

        stavka.setSetva(s);

        assertNotNull(stavka.getSetva());
        assertEquals(1L, stavka.getSetva().getSetvaID());
    }

    @Test
    void testSetAktivnost() {
        Aktivnost a = new Aktivnost();
        a.setAktivnostID(1L);

        stavka.setAktivnost(a);

        assertNotNull(stavka.getAktivnost());
        assertEquals(1L, stavka.getAktivnost().getAktivnostID());
    }

    @Test
    void testToString() {
        stavka.setId(1L);
        stavka.setCena(2500.0);
        stavka.setDatum(LocalDate.of(2025, 3, 15));

        assertTrue(stavka.toString().contains("1"));
        assertTrue(stavka.toString().contains("2500"));
        assertTrue(stavka.toString().contains("2025-03-15"));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false"
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        stavka.setId(id1);

        StavkaSetve s2 = new StavkaSetve();
        s2.setId(id2);

        assertEquals(ocekivano, stavka.equals(s2));
    }
}
