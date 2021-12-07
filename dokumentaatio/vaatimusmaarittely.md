# Vaatimusmaarittely

## Sovelluksen tarkoitus

Sovellus on perinteinen matopeli. Pelaaja ohjaa matoa ja tarkoituksena on syödä ruokaa ja siten kerätä pisteitä mahdollisimman paljon ennen kuin mato osuu seinään tai itseensä, jolloin peli päättyy.

## Käyttäjät

Sovelluksessa on vain peruskäyttäjiä. 

## Käyttöliittymäluonnos

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittymaluonnos.png" width="500">

Sovellus alkaa kirjautumisnäkymästä, josta voi siirtyä joko suoraan "logged in" -osioon tai rekisteröintisivun kautta. "Logged in" -sivusta pääsee joko pelaamaan peliä tai katsomaan parhaimpia tuloksia "high scores"-sivulla. Häviämisen jälkeen voidaan siirtyä uuteen peliin, tarkastelemaan parhaimpia tuloksia tai kirjautumalla pois. 


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista:

- :heavy_check_mark: Pelissä on näkymä, jossa on nappi, josta päästään pelaamaan peliä 
- Käyttäjä voi luoda uuden tunnuksen
- Käyttäjä voi kirjautua sisään jo olemassaolevilla tunnuksilla

### Kirjautumisen jälkeen:

- :heavy_check_mark: Käyttäjä voi pelata peliä
- Käyttäjä voi tarkistaa parhaimpia tuloksia
- Käyttäjä voi kirjautua ulos

### Pelin aikana: 

- :heavy_check_mark: Käyttäjä pääsee pelaamaan perinteistä matopeliä

### Matopelin toiminallisuudet:

- :heavy_check_mark: Käyttäjä pystyy ohjaamaan matoa WASD-näppäimillä
- :heavy_check_mark: Pistemäärä kasvaa madon syötyään ruuan, mato kasvaa ja madon nopeus kiihtyy
- :heavy_check_mark: Madon häntä seuraa päätä
- :heavy_check_mark: Peli loppuu, kun mato osuu reunoihin tai itseensä 

### Pelin päätyttyä: 

- Käyttäjä näkee oman tuloksensa. 
- Käyttäjä voi pelata pelin uudestaan
- Käyttäjä voi siirtyä tarkistamaa parhaimpia tuloksia
- Käyttäjä voi kirjautua ulos
