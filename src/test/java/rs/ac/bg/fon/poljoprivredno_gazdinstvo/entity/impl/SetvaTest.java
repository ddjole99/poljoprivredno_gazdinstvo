package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SetvaTest {

    private Setva setva;

    @BeforeEach
    void setUp() {
        setva = new Setva();
    }

    @AfterEach
    void tearDown() {
        setva = null;
    }

    @Test
    void testSetSetva() {
        setva.setSetvaID(1L);
        assertEquals(1L, setva.getSetvaID());
    }

    @Test
    void testSetDatumPocetka() {
        LocalDate datum = LocalDate.of(2025, 5, 5);
        setva.setDatumPocetka(datum);

        assertEquals(datum, setva.getDatumPocetka());
    }

    @Test
    void testSetDatumZavrsetka() {
        LocalDate datum = LocalDate.of(2025, 6, 5);
        setva.setDatumZavrsetka(datum);

        assertEquals(datum, setva.getDatumZavrsetka());
    }

    @Test
    void testSetStatus() {
        setva.setStatus(Status.PLANIRANA);
        assertEquals(Status.PLANIRANA, setva.getStatus());
    }

    @Test
    void testSetAdministrator() {
        Administrator a = new Administrator();
        a.setAdministratorID(1L);

        setva.setAdministrator(a);

        assertNotNull(setva.getAdministrator());
        assertEquals(1L, setva.getAdministrator().getAdministratorID());
    }

    @Test
    void testSetParcela() {
        Parcela p = new Parcela();
        p.setParcelaID(1L);

        setva.setParcela(p);

        assertNotNull(setva.getParcela());
        assertEquals(1L, setva.getParcela().getParcelaID());
    }

    @Test
    void testSetKultura() {
        Kultura k = new Kultura();
        k.setKulturaID(1L);

        setva.setKultura(k);

        assertNotNull(setva.getKultura());
        assertEquals(1L, setva.getKultura().getKulturaID());
    }

    @Test
    void testSetStavkeSetve() {
        List<StavkaSetve> lista = new ArrayList<>();
        lista.add(new StavkaSetve(1L));
        lista.add(new StavkaSetve(2L));
        
        setva.setStavkeSetve(lista);

        assertNotNull(setva.getStavkeSetve());
        assertSame(lista, setva.getStavkeSetve());
        assertEquals(2, setva.getStavkeSetve().size());
    }

    @Test
    void testToString() {
        setva.setSetvaID(1L);
        setva.setDatumPocetka(LocalDate.of(2025, 5, 5));
        setva.setDatumZavrsetka(LocalDate.of(2025, 6, 5));
        setva.setStatus(Status.PLANIRANA); 

        assertTrue(setva.toString().contains("setvaID=1"));
        assertTrue(setva.toString().contains("2025-05-05"));
        assertTrue(setva.toString().contains("2025-06-05"));
        assertTrue(setva.toString().contains("PLANIRANA"));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false"
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        setva.setSetvaID(id1);

        Setva s2 = new Setva();
        s2.setSetvaID(id2);

        assertEquals(ocekivano, setva.equals(s2));
    }
}
