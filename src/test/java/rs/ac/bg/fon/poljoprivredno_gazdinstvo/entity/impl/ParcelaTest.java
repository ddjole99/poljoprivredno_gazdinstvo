package rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParcelaTest {

    private Parcela parcela;

    @BeforeEach
    void setUp() {
        parcela = new Parcela();
    }

    @AfterEach
    void tearDown() {
        parcela = null;
    }

    @Test
    void testSetParcela() {
        parcela.setParcelaID(1L);
        assertEquals(1L, parcela.getParcelaID());
    }

    @Test
    void testSetNaziv() {
        parcela.setNaziv("Parcela 1");
        assertEquals("Parcela 1", parcela.getNaziv());
    }

    @Test
    void testSetLokacija() {
        parcela.setLokacija("Pored reke");
        assertEquals("Pored reke", parcela.getLokacija());
    }

    @Test
    void testSetPovrsina() {
        parcela.setPovrsina(5.5);
        assertEquals(5.5, parcela.getPovrsina());
    }

    @Test
    void testSetTipZemljista() {
        TipZemljista tip = new TipZemljista();
        tip.setTipZemljistaID(1L);

        parcela.setTipZemljista(tip);

        assertNotNull(parcela.getTipZemljista());
        assertEquals(1L, parcela.getTipZemljista().getTipZemljistaID());
    }

    @Test
    void testToString() {
        parcela.setNaziv("Parcela 1");
        parcela.setLokacija("Pored reke");
        parcela.setPovrsina(6.2);
        parcela.setTipZemljista(new TipZemljista(1L));

        assertTrue(parcela.toString().contains("Parcela 1"));
        assertTrue(parcela.toString().contains("Pored reke"));
        assertTrue(parcela.toString().contains("6.2"));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, true",
        "1, 2, false"
    })
    void equalsTest(Long id1, Long id2, boolean ocekivano) {
        parcela.setParcelaID(id1);

        Parcela p2 = new Parcela();
        p2.setParcelaID(id2);

        assertEquals(ocekivano, parcela.equals(p2));
    }
}
