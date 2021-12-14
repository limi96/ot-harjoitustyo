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

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/rekisteroytyminen.png">

# Pelin aloittaminen

Kun olet kirjautunut sisään pääset aloitusnäkymään. Paina "Pelaa peliä :)"-painiketta aloittaaksesi pelin. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pelinpelaaminen.png">






