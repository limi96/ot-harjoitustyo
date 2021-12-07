# Ohjelmistotekniikka, harjoitustyö

## Matopeli

Tämä projekti on osana Helsingin yliopiston Ohjelmistotekniikka-kurssia. 

Sovelluksen avulla on mahdollista pelata matopeliä. Sovellus mahdollistaa myös pisteiden tallenuksen käyttäjänimellä.

## Javan versio

Sovellus on kirjoitettu Javan versiolle 11 ja käyttää JavaFX:ää. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Release

[Viikko 5 release](https://github.com/limi96/ot-harjoitustyo/releases/tag/viikko5)

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



