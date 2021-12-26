# Arkkitehtuuri

## Pakkauskaavio

<img src= "https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png">

## Sovelluslogiikka

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka.png">

## DAO:n implementointi ja tietokanta

DAO:ta käsittelee matopeli.dao -paketti. Ohjelma käsittelee tietojen tallentamisen Dao-rajapinnan kautta, joka puolestaan taas toteuttaa toiminnat UserDao-luokan kautta. 

DAO on toteutettu ohjelmassa tietokannan kautta. Tietokanta puolestaan taas on toteutettu SQLite:n kautta. SQLite toteutus UserDao-luokassa on tehty noudattaen Tietokantojen perusteet Kevät 2020 mooc-ohjetta, joka on saatavilla [tästä](https://tikape-k20.mooc.fi/sqlite-java).

SQLite yhteys luodaan alustavasti käyttäen ```config.properties``` tiedostoa, jossa on 
```
databaseURL=jdbc:sqlite:gameDatabase.db
testDatabaseURL=jdbc:sqlite:testDatabase.db
```
DatabaseURL-osoitetta käytetään sovelluksen käynnistyksen yhteydessä gameDatabase.db -tiedoston luomisessa, jos se ei ole valmiiksi kyseisessä hakemistossa. Tämän kautta UserDao-luokka luo taulukon "users", jossa on sarakkeina "user_id SERIAL PRIMARY KEY, username TEXT UNIQUE, password TEXT, score INTEGER". 


## Sekvenssikaaviot

### Käyttäjän Kirjautuminen

Ui-luokka alustaa LoginSceneController-luokan ja asettaa kirjautumisnäkymän. Samalla LoginSceneController luokka alustaa Dao-rajapinnan "User"-toimintoja varten käyttämällä ```config.properties```-tiedostoa tietokantayhteyden luomiseksi. 

Kun käyttäjä painaa nappia "Kirjaudu" LoginSceneControllerin ```handleLogin()``` funktion kautta Dao-rajapinta käsittelee kirjautumisen SQLiten kautta metodilla ```loginSuccess()```. Tämä palauttaa totuusarvon LoginSceneControllerille. Jos vastaus oli true, niin LoginSceneController välittää Ui:lle asetettavaksi aloitusnäkymän metodilla ```setIntroScene```. Näkymä siirtyy aloitusnäkymään. 

<img src ="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kirjautuminen-sekvenssikaavio.png">

### Käyttäjän rekisteröytyminen

Ui-luokka alustaa RegisterSceneController-luokan ja asettaa rekisteröytymisnäkymän. Ui-luokassa linkitetään LoginSceneController RegisterSceneControlleriin ja tämä saa DAO-rajapinnan itselleen LoginSceneControllerin kautta. 

Kun käyttäjä painaa nappia "Rekisteröidy" RegisterSceneControllerin ```handleRegistration()``` funktion kautta Dao-rajapinta käsittelee rekisteröytymisen SQLiten kautta metodilla ```registerUser()```. Tämä palauttaa totuusarvon RegisterSceneControllerille. 
Käyttäjä voi painaa tämän jälkeen nappia "Takaisin", jolloin RegisterSceneController välittää Ui:lle asetettavaksi kirjautumisnäkymän metodilla ```setLoginScene```. Näkymä siirtyy kirjautumisnäkymään. 

<img src ="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/rekisteroi%20-sekvenssikaavio.png">


### Varsinaisen matopeli-näkymän käynnistyminen

Ui-luokan alustuksen jälkeen, introScene-näkymässä olevan "Pelaa peliä :)" -nappia painatteassa introController-luokan ``` handleInitGame()```  -metodin kautta Ui asettaa näkymäksi``` gameScenen``` eli varsinaisen pelinäkymän ```setGameWindow(Color, Color, true/false)```-metodin kautta , jossa on parametreina Intro-näkymässä valitut värivaihtoehdot. GameSceneController alustaa GameWindow:n luoman pelinäkymän Ui:lle. Lisäksi GameWindow saa GameSceneController-luokan injektion, jonka avulla GameWindow voi käsitellä pelin loppumista metodilla ```handleGameOver()```.

Pelinäkymän hakemiseen käskytetään ensin GameSceneControlleria hakemaan GameWindow metodilla ```getGameWindow()```. GameSceneController pyytää GameWindow:ta rakentamaan pelinäkymän metodilla ```getGameScene(Color, Color, true/false)```. Nyt Ui voi vihdoin asettaa itselleen pelinäkymän metodilla ```setGameScene()```. 

GameWindow-luokka alustaa SnakeClassin ja Food-luokkien kautta pelille tarpeelliset Oliot ja pelinäkymän ``` initializeSnake()```, ```initializeScore()``` ja ``` initializeRootpane()``` -metodien kautta. Peli käynnistetään ```startGame()```- metodin kautta ja lisätään ohjaustoiminto ```addKeyListener()``` -metodin kautta. Nyt vihdoinkin on rakennettu pelinäkymä ```gameScene```, joka palautetaan Ui-luokalle ja tämä asettaa pelinäkymän käyttäjälle. 

Peli päivittyy jatkuvasti ``` render() ``` -metodin kautta, jolloin SnakeClass-olion kautta voidaan säätää maton nopeus, pituus ja suunta. Pelin loppuessa GameWindow kutsuu GameSceneController-luokan ```handleGameOver()```-metodia, jolloin Ui-luokka asettaa ```setGameOverScene```-metodin kautta pelin loppunäkymän. 


<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/matopeli-sekvenssikaavio.png">

### Loppunäkymä - Pisteiden tallennus ja uudelleenkäynnistyminen 

Ui-luokka alustaa loppunäkymän eli GameOverScenen ja ennen käynnistymistä Ui-luokassa linkitetään LoginSceneController GameSceneControlleriin ja tämä saa DAO-rajapinnan itselleen LoginSceneControllerin kautta. 

Kun peli päättyy GameWindow-luokka pyytää GameSceneControlleria käsittelemään pelin loppumista handleGameOver()-metodin kautta. 
Tämä hoitaa pelin pisteiden tallentumisen käyttäjälle ensin hakemalla pisteet GameWindow-luokalta, sitten käyttäjänimen LoginSceneControllerilta ja tekee lopulta tallenuksen tietokantaa DAO-rajapinnan kautta. Lopuksi GameSceneController ilmoittaa käyttäjälle Label-olion kautta lopullisen pistemäärän. 

Pelin uudelleen käynnistyminen tapahtuu siten, että GameSceneController pyytää GameWindow-luokalta edellisen pelin väriasetukset. Tämän jälkeen GameSceneController hoitaa pelin käynnistymisen ```handleRestartGame()```-metodin kautta. Pelinäkymä luodaan kokonaan uudestaan Ui:lle edellisen sekvenssikaavion kuvailemalla tavalla. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/loppunakyma-sekvenssi.png">


### Highscores-näkymän luominen


Ui-luokka alustaa highscores-näkymän ja ennen käynnistymistä Ui-luokassa linkitetään LoginSceneController HighScoresSceneControlleriin ja tämä saa DAO-rajapinnan itselleen LoginSceneControllerin kautta. 

HighScoresSceneControllerin käynnistyessä ```start()```-metodilla se hakee DAO-rajapinnan kautta metodilla ```fetchHighScores()``` tietokannasta käyttäjien pistetilanteet. Tämän jälkeen saatu listan tiedot käsitellään ja asetetaan käyttäjälle näkyviin ```nameLabel``` ja ```scoreLabel``` Label-olioden avulla. 

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/highscoret-sekvenssikaavio.png">




