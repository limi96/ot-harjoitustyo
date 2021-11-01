/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author limi
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(100000);
    }
    
    @Test
    public void kassaAlkuSaldo() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaAlkuLounasMyytyMaara() {
        int yhteensa = kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty();
        assertEquals(0, yhteensa);
    }

    @Test
    public void vaihtorahaEdullinenKateisella() {
        assertEquals(1, kassa.syoEdullisesti(241));
    }
    
    @Test
    public void vaihtorahaMaukasKateisella() {
        assertEquals(1, kassa.syoMaukkaasti(401));
    }
    
    @Test
    public void edullinenKateisellaRahatEiRiita() {
        assertEquals(1, kassa.syoEdullisesti(1));
    }
    
    @Test
    public void maukasKateisellaRahatEiRiita() {
        assertEquals(1, kassa.syoMaukkaasti(1));
    }
    
    
    
    @Test
    public void edullinenKateisellaLounasMaaraKasvaa() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKateisellaLounasMaaraKasvaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKateisellaLounasMaaraEiKasva() {
        kassa.syoEdullisesti(1);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKateisellaLounasMaaraEiKasva() {
        kassa.syoMaukkaasti(1);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void edullinenKateisellaKassaKasvaa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKateisellaKassaKasvaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullinenKateisellaKassaEiKasva() {
        kassa.syoEdullisesti(1);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKateisellaKasvaEiKasva() {
        kassa.syoMaukkaasti(1);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
//Korttimaksut
   
    @Test
    public void maksuEdullinenKortilla() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maksuMaukasKortilla() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maksuEiToimiEdullinenKortilla() {
        kortti = new Maksukortti(1);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maksuEiToimiMaukasKortilla() {
        kortti = new Maksukortti(1);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullinenKortillaMaaraOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals(99760, kortti.saldo());
    }
    
    @Test
    public void maukasKortillaMaaraOikein() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(99600, kortti.saldo());
    }
    
    @Test
    public void maksuEiToimiEdullinenKortillaRahaSama() {
        kortti = new Maksukortti(1);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kortti.saldo());
    }
    
    @Test
    public void maksuEiToimiMaukasKortillaRahaSama() {
        kortti = new Maksukortti(1);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kortti.saldo());
    }
    
    
    @Test
    public void edullinenKortillaLounasMaaraKasvaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKortillaLounasMaaraKasvaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKortillaLounasMaaraEiKasva() {
        kortti = new Maksukortti(1);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKortillaLounasMaaraEiKasva() {
        kortti = new Maksukortti(1);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKortillaKassaRahaEimuutu() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKortillaKassaRahaEimuutu() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
  
    @Test
    public void lataaRahaaKorttiArvoOikein() {
        kassa.lataaRahaaKortille(kortti, 100000);
        assertEquals(200000, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKorttiArvoOikeinEiNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -100000);
        assertEquals(100000, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKassaArvoOikein() {
        kassa.lataaRahaaKortille(kortti, 100000);
        assertEquals(200000, kassa.kassassaRahaa());
    }
    
        
    @Test
    public void korttiArvoOikein() {
        assertEquals("saldo: 1000.0", kortti.toString());
    }
    
}
