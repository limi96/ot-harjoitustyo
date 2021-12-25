# Testausdokumentti 

Ohjelman testaaminen on toteutettu kokonaisuudessan JUnitilla. Koska tällä kurssilla emme testaa UI-liittyviä asioita, 
UI-liittyvät osat ovat testattu manuaalisesti ohjelman kehityksen aikana. 

## Yksikkö -ja integraatiotestaus

### DAO:n totetutus (UserDao-luokka)

UserDao -luokkaa on testattu valetietokannalla, joka on nimellä ```testDatabase.db``` ```config.properties``` -tiedostossa. 
Testauksessa on käytynä läpi kaikki UserDao-luokan metodit. 

### Sovelluslogiikka 

Ohjelmassa pelilogiikka ja käyttöliittymä ovat toisistaan erillään, joten pelilogiikkaa testataan vain SnakeClassTest -ja FoodTest-luokkien kautta. Käyttöliittymä vastaa vain SnakeClass-luokan olion luomisesta ja väriasetuksien valinnoista. 
SnakeClass -ja Food-luokkien värivalintoja testataan GameWindow-luokan kautta. Sekä SnakeClassTest että FoodTest jättävät pois testauksessaan gettereitä ja settereitä. 

### Testauskattavuus 

Käyttöliittymää lukuunottamatta ohjelman testikattavuus riveittäin on 99 % ja haarautumakattavuus 85 %. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testauskattavuus.png">

SnakeClass-luokassa jätettiin testaamatta tiettyjä if-ehtoja
SnakeClass-luokassa on monia if-ehtoja, jotka tarkistavat, ovatko kahden olion sijainnit samoja. Näihin liittyy noin noin 15 % "missed branches", sillä if-ehtojen päinvastaista tilannetta ei ole testattu. Päinvastaisia if-tilanteita ei ole kuitenkaan mielekästä testata kyseisessä tapauksessa, sillä näiden kohdalla peli vain jatkaa normaalia toimintaansa. Esimerkiksi collideWithBorder() ja touchesFood()-metodien myötä peli vaa jatkaa etenemistään, silloin kun if-ehto ei toteudu. 

UserDao-luokan metodissa on myös haarautuma käsittelemättä registerUser()-metodin kohdalla. Haarautumaa ei käsitellä, sillä ei ole olemassa tilannetta, joka muuttaisi statement.executeUpdate() -arvoksi alle nollaa. Siksi tämä haarautuma on jätetty käsittelemättä. 


## Järjestelmätestaus 

Koska käyttöliittymä on jätetty pois, ohjelman järjestelmätestaus on suoritettu vain manuaalisesti. 

### Asennus ja konfigurointi 



### Toiminnallisuudet 

Sovellus määrittelee virheelliset syötteet vain tyhjiksi tai välilyöntiä sisältäviksi. Ohjelma käsittelee tämmöiset tapaukset erikseen. Lisäksi kaikki määrittelydokumentin listaamat toiminnallisuudet ovat käyty läpi. 


### Sovellukseen jäänet laatuongelmat

JavaFX:ssä pystytään tarkistamaan vain Rectangle-muuttujan keskikohtaa getTranslateX/Y()-metodien kautta. Esimerkiksi, jos neliö on 20 pikseliä leveä, niin keskikohdasta 10 pikseliä oikealle ja vasemmalle on pelkkää tyhjää. Tämä aiheuttaa sen, että madon osat voivat mennä hieman päällekkäin, koska osutaan nimenomaan tällaiselle tyhjälle alueelle. 

Ohjelma ei toimi ollenkaan jos ```config.properties```-tiedosto ei löydy samasta suoritettavasta kansiosta. Ohjelman pystyy suorittamaan vain komentorivin kautta ```java -jar ``` -komennon kautta.

