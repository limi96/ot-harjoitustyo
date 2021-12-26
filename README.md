# Ohjelmistotekniikka, harjoitustyö

## Matopeli

Tämä projekti on osana Helsingin yliopiston Ohjelmistotekniikka-kurssia. 
Sovelluksen avulla on mahdollista pelata matopeliä. Sovellus mahdollistaa myös pisteiden tallenuksen käyttäjänimellä.
Lopuksi voidaan tarkistaa highscores-näkymästä käyttäjien Top 5 korkeimmat pisteet. 

## Javan versio

Sovellus on kirjoitettu Javan versiolle 11 ja käyttää JavaFX:ää. 

## Dokumentaatio

[Käyttöohje](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Vaatimusmäärittely](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Release

[Viikko 5 release](https://github.com/limi96/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6 release](https://github.com/limi96/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/limi96/ot-harjoitustyo/releases/tag/loppupalautus)

## Sovelluksen käyttö

HUOM! Siirry ensiksi matopeli-hakemistoon. Vasta sitten komennot toimivat. 

### Käynnistäminen
 Tämän jälkeen suorita terminaalissa komento:

```
mvn compile exec:java -Dexec.mainClass=matopeli.Main
```

### JUnit testaus

```
mvn test
```

### Testikattavuusraportti 


```
mvn jacoco:report
```

### Checkstyle-raportin luominen
```
mvn jxr:jxr checkstyle:checkstyle
```

### Jar-generointi

```
mvn package
```
Jar-tiedosto löytyy tämän jälkeen target-kansiosta



