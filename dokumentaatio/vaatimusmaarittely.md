# Vaatimusmaarittely

## Sovelluksen tarkoitus

Sovellus on perinteinen matopeli. Pelaaja ohjaa matoa ja tarkoituksena on syödä ruokaa ja siten kerätä pisteitä mahdollisimman paljon ennen kuin mato osuu seinään tai itseensä, jolloin peli päättyy.

## Käyttäjät

Sovelluksessa on vain peruskäyttäjiä. 

## Käyttöliittymäluonnos

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittymaluonnos.png" width="800">

Sovellus alkaa kirjautumisnäkymästä, josta voi siirtyä joko suoraan Intro-näkymään tai rekisteröytymään. Intro-näkymästä pääsee joko pelaamaan peliä tai katsomaan parhaimpia tuloksia "high scores"-sivulla. Häviämisen jälkeen voidaan siirtyä uuteen peliin, tarkastelemaan parhaimpia tuloksia tai kirjautumalla pois. 


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista:

- :heavy_check_mark: Pelissä on näkymä, jossa on nappi, josta päästään pelaamaan peliä 
- :heavy_check_mark: Käyttäjä voi luoda uuden tunnuksen
- :heavy_check_mark: Käyttäjä voi kirjautua sisään jo olemassaolevilla tunnuksilla
- :heavy_check_mark: Kirjautuessa ja rekisteröinnin aikana tarkistetaan tyhjät ja välilyöntejä sisältävät syötteet 
- :heavy_check_mark: Rekisteröinnin aikana tarkistetaan, onko olemassa jo samanniminen käyttäjätunnus
- :heavy_check_mark: Virhetekstit eivät jää lojumaan siirtyessä näkymästä toiseen kirjautumis -ja rekisteröintinäkymissä. 

### Kirjautumisen jälkeen:

- :heavy_check_mark: Käyttäjä voi pelata peliä
- :heavy_check_mark: Käyttäjä voi tarkistaa parhaimpia tuloksia
- :heavy_check_mark: Käyttäjä voi kirjautua ulos
- :heavy_check_mark: Käyttäjä voi valita madon, ruuan ja taustan värit
- :heavy_check_mark: Jos madon tai ruuan väriksi on valittu musta tai valkonen, kyseessä oleva olion väri muuttuu oletusarvoiseksi. 
- :heavy_check_mark: Intro-näkymässä on ohjeet pelin aloittamista varten. 

### Pelin aikana: 

- :heavy_check_mark: Käyttäjä pääsee pelaamaan perinteistä matopeliä
- :heavy_check_mark: Pelin aikana on mahdollista tauottaa peli painamalla p-näppäintä 
- :heavy_check_mark: Tauotettu peli jatkaa toimintaansa painaessa p-näppäintä uudestaan. 
- :heavy_check_mark: "Peli tauotettu" -teksti näkyy sekä valkoisella että mustalla taustalla. 

### Matopelin toiminallisuudet:

- :heavy_check_mark: Käyttäjä pystyy ohjaamaan matoa WASD-näppäimillä
- :heavy_check_mark: Pistemäärä kasvaa madon syötyään ruuan, mato kasvaa ja madon nopeus kiihtyy
- :heavy_check_mark: Madon häntä seuraa päätä
- :heavy_check_mark: Peli loppuu, kun mato osuu reunoihin tai itseensä 

### Pelin päätyttyä: 

- :heavy_check_mark: Käyttäjä näkee oman tuloksensa. 
- :heavy_check_mark: Käyttäjä voi pelata pelin uudestaan
- :heavy_check_mark: Käyttäjä voi siirtyä tarkistamaa parhaimpia tuloksia
- :heavy_check_mark: Tietokantaan tallennetaan vain käyttäjän paras tulos. Tällöin ei tule highscores-näkymään ei tule monta listausta samalta käyttäjältä
- :heavy_check_mark: Käyttäjä voi kirjautua ulos
- :heavy_check_mark: Käyttäjien tiedot pysyvät tallessa sovelluksen suljettua edellyttäen sitä, että gameDatabse.db-tietokantatiedosto pysyy tallella. 
