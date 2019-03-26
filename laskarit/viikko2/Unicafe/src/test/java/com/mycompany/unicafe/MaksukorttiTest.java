package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortinSaldoEiMuutuJosSaldoEiRiita() {
        Kassapaate kassa = new Kassapaate();
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaFalseJosSaldoEiRiita() {
        Kassapaate kassa = new Kassapaate();
        assertFalse(kassa.syoEdullisesti(kortti));
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void metodiPalauttaaTrueJosSaldoEiRiita() {
        Kassapaate kassa = new Kassapaate();
        kortti.lataaRahaa(100000);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosSaldoEiRiita() {
        assertFalse(kortti.otaRahaa(100));
    }
}
