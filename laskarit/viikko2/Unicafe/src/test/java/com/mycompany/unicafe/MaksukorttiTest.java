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
    public void alkuSaldoOikein() {
        assertEquals(10, kortti.saldo()); 
    }
    @Test
    public void latausToimii() {
        kortti.lataaRahaa(1);
        assertEquals(11,kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeJosOnRahaa() {
        assertEquals(true, kortti.otaRahaa(1));      
    }
    
    @Test
    public void saldoVaheneeJosEiRahaa() {
        assertEquals(false, kortti.otaRahaa(99));      
    }
    
    
    
    
    
}
