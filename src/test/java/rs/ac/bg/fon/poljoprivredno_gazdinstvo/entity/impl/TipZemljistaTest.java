package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TipZemljistaTest {

    private TipZemljista tipZemljista;

    @BeforeEach
    void setUp() {
        tipZemljista = new TipZemljista();
    }

    @AfterEach
    void tearDown() {
        tipZemljista = null;
    }

    @Test
    void testSetTipZemljista() {
        tipZemljista.setTipZemljistaID(1L);
        assertEquals(1L, tipZemljista.getTipZemljistaID());
    }

    @Test
    void testSetNaziv() {
        tipZemljista.setNaziv("Crnica");
        assertEquals("Crnica", tipZemljista.getNaziv());
    }

    @Test
    void testSetPhVrednost() {
        tipZemljista.setPhVrednost(6.5);
        assertEquals(6.5, tipZemljista.getPhVrednost());
    }

    @Test
    void testSetPlodnost() {
        tipZemljista.setPlodnost("Visoka");
        assertEquals("Visoka", tipZemljista.getPlodnost());
    }

    @Test
    void testToString() {
        tipZemljista.setNaziv("Crnica");
        
        assertTrue(tipZemljista.toString().contains("Crnica"));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false"
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        tipZemljista.setTipZemljistaID(id1);

        TipZemljista t2 = new TipZemljista();
        t2.setTipZemljistaID(id2);

        assertEquals(ocekivano, tipZemljista.equals(t2));
    }
}
