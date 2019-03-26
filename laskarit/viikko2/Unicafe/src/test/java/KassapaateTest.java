/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author skajanti
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunKassapaatteenRahamaaraOikea(){
        String vastaus = String.valueOf(kassa.kassassaRahaa());
        assertEquals("100000", vastaus);
    }
    
    @Test
    public void luodunKassapaatteenMyytyjenLounaidenMaaraOnOikea(){
        String vastaus = String.valueOf(kassa.edullisiaLounaitaMyyty());
        String vastaus2 = String.valueOf(kassa.maukkaitaLounaitaMyyty());
        assertEquals("0", vastaus);
        assertEquals("0", vastaus2);
    }
    
    @Test
    public void syoEdullisestiKateisellaToimii() {
        String vastaus = String.valueOf(kassa.syoEdullisesti(1000));
        String vastaus2 = String.valueOf(kassa.syoEdullisesti(10));
        assertEquals("760", vastaus);
        assertEquals("10", vastaus2);
        String vastaus3 = String.valueOf(kassa.edullisiaLounaitaMyyty());
        assertEquals("1", vastaus3);
    }
    
    @Test
    public void syoMaukkaastiKateisellaToimii() {
        String vastaus = String.valueOf(kassa.syoMaukkaasti(1000));
        String vastaus2 = String.valueOf(kassa.syoMaukkaasti(10));
        assertEquals("600", vastaus);
        assertEquals("10", vastaus2);
        String vastaus3 = String.valueOf(kassa.maukkaitaLounaitaMyyty());
        assertEquals("1", vastaus3);
    }
    
    @Test
    public void syoEdullisestiKortilolaToimii() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassa.syoEdullisesti(kortti));
        String vastaus = String.valueOf(kortti.saldo());
        assertEquals("760", vastaus);
        String vastaus2 = String.valueOf(kassa.edullisiaLounaitaMyyty());
        assertEquals("1", vastaus2);
        kortti.otaRahaa(760);
        assertFalse(kassa.syoEdullisesti(kortti));
        String vastaus3 = String.valueOf(kassa.edullisiaLounaitaMyyty());
        assertEquals("1", vastaus3);
        String vastaus4 = String.valueOf(kassa.kassassaRahaa());
        assertEquals("100000", vastaus4);
    }
    
    @Test
    public void syoMaukkaastiKortilolaToimii() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassa.syoMaukkaasti(kortti));
        String vastaus = String.valueOf(kortti.saldo());
        assertEquals("600", vastaus);
        String vastaus2 = String.valueOf(kassa.maukkaitaLounaitaMyyty());
        assertEquals("1", vastaus2);
        kortti.otaRahaa(600);
        assertFalse(kassa.syoMaukkaasti(kortti));
        String vastaus3 = String.valueOf(kassa.maukkaitaLounaitaMyyty());
        assertEquals("1", vastaus3);
        String vastaus4 = String.valueOf(kassa.kassassaRahaa());
        assertEquals("100000", vastaus4);
    }
    
    @Test
    public void kortilleRahanLatausToimii() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.lataaRahaaKortille(kortti, 1000);
        String vastaus = String.valueOf(kortti.saldo());
        assertEquals("2000", vastaus);
        String vastaus2 = String.valueOf(kassa.kassassaRahaa());
        assertEquals("101000", vastaus2);
        kassa.lataaRahaaKortille(kortti, -3);
        String vastaus3 = String.valueOf(kassa.kassassaRahaa());
        assertEquals("101000", vastaus3);
    }
}
