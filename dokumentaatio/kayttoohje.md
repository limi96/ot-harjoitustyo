# Käyttöohje

Lataa tiedosto [Matopeli-1.0-SNAPSHOT.jar](https://github.com/limi96/ot-harjoitustyo/releases/tag/viikko6)

# Konfigurointi

Ohjelma olettaa, että käynnistymishakemistossa on tiedosto ```config.properties```-tiedosto. 
Tämä määrittelee tietokantayhteyden ja luo tietokanta-tiedoston .db-päätteellä. Tiedoston sisältö on seuraavanalainen: 

```
databaseURL=jdbc:sqlite:gameDatabase.db
testDatabaseURL=jdbc:sqlite:testDatabase.db

```
# Ohjelman käynnistäminen 

Ohjelma käynnistyy komennolla 
```java -jar Matopeli-1.0-SNAPSHOT.jar```

Huomioi, että  ```config.properties```-tiedosto on samassa hakemistossa. 

# Ensimmäinen näkymä

Ohjelman käynnistyessä pääset kirjautumis-näkymään. Kirjautumis-näkymä on samalla myös ensimmäinen näkymä.

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ensimmainen.png">

# Kirjautuminen

Kirjautuminen tapahtuu syöttämällä käyttäjätunnus, salasana ja painamalla "kirjaudu"-painiketta. 
Jos ei ole käyttäjätunnuksia, paina "rekisteröydy"-nappia. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kirjautuminen.png">

# Rekisteröytyminen

Syötä haluttu käyttäjätunnus ja salasana ja kirjoita sama salasana uudestaan. Tämän jälkeen paina "rekisteröidy"-nappia. 
Huomioi, että tietokanta ei salli samoja käyttäjätunnuksia, joten valitse toinen käyttäjätunnus, jos se on jo varattu. 

Paina lopuksi "takaisin"-nappia niin pääset takaisin kirjautumis-näkymään ja voit kirjautua sisään. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/rekisteroityminen.png">

# Pelin aloittaminen

Kun olet kirjautunut sisään pääset aloitusnäkymään eli Intro-näkymään. Voit valita madon ja ruuan värejä värivalikkojen kautta. Näistä en saanut valitettavasti kuvia, koska se sulkeutuu aina, kun ottaa kuvaa (esim. Print Screenin kautta). Taustan väriksi voi valita mustan tai valkosen radio-painikkeiden kautta. Näkymässä on myös ohjetekstit. Lue ne huolellisesti läpi. 

Paina "Pelaa peliä :)"-painiketta aloittaaksesi pelin. 
Jos haluat nähdä nykyiset highscoret paina "Katso highscoret!"-nappia.
Jos haluat kirjautua ulos, paina "kirjaudu ulos" -nappia niin pääset takaisin kirjautumisnäkymään. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pelinpelaaminen.png">

# Pelin pelaaminen

Peliä voit pelata nuolinäppäimillä tai WASD-näppäimillä. 
Voit tauottaa pelin p-näppäimellä, jolloin tulee "Peli tauotettu" -teksti näkyviin ja pelin on tilapäisesti pysäytetty. Kun painat toisen kerran p-näppäintä, peli jatkuu. 

Seiniin ei saa osua, eikä mato saa myöskään osua itseensä, muuten peli päättyy. 
Ylävasemalla näkyy sininen "Pisteet : 0" teksti, jossa näkyy pistetilanne. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pelinakyma.png">


# Pelin päättyminen 

Pelin päättyessä näet päättymishetken pistetilanteesi ja voit jatkaa siitä erilaisiin näkymiin. Jos sait pisteitä enemmän kuin koskaan aiemmin, tämä pistemäärä päivittyy tietokantaan. 

"Aloita uusi peli!" -nappi käynnistää pelin uudelleen samoilla väreillä kuin, mitä valitsit ensimmäisen kerran peliä pelattaessa. 
Kuten aiemmin napista "Katso highscoret!" pääset katsomaan highscoreja. 
"Takaisin introon "-napista pääset samaan kohtaan kuin pelin aloittaminen -kohdassa kuvattuun Intro-näkymään. 
"Kirjaudu ulos" -nappi toimii samalla tavalla kuin Intro-näkymässä. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pelipaattyi.png">

# Highscoret tarkastaminen

"Katso highscoret!" -nappia painamalla siirryt highscoret-näkymään, joka tulostaa esille korkeimmat pistemäärät, mitä ollaan saavutettu. Huomaa, että jos   ```config.properties```-tiedosto poistetaan ja luodaan uudelleen, pistemäärät katoavat. 

Pääset tästä näkymästä takaisin introon painamala "Takaisin introon!" -nappia. 







