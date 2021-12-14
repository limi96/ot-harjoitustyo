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

### Käyttäjän rekisteröytyminen

Ui-luokka alustaa RegisterSceneController-luokan ja asettaa rekisteröytymis. Samalla RegisterSceneController luokka alustaa Dao-rajapinnan "User"-toimintoja varten. 

Kun käyttäjä painaa nappia "Rekisteröidy" RegisterSceneControllerin ```handleRegistration()``` funktion kautta Dao-rajapinta käsittelee rekisteröytymisen SQLiten kautta metodilla ```registerUser()```. Tämä palauttaa totuusarvon RegisterSceneControllerille. 
Käyttäjä voi painaa tämän jälkeen nappia "Takaisin", jolloin RegisterSceneController välittää Ui:lle asetettavaksi kirjautumisnäkymän metodilla ```setLoginScene```. Näkymä siirtyy kirjautumisnäkymään. 

<img src ="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/rekisteroi%20-sekvenssikaavio.png">

### Käyttäjän Kirjautuminen

Ui-luokka alustaa LoginSceneController-luokan ja asettaa kirjautumisnäkymän. Samalla LoginSceneController luokka alustaa Dao-rajapinnan "User"-toimintoja varten. 

Kun käyttäjä painaa nappia "Kirjaudu" LoginSceneControllerin ```handleLogin()``` funktion kautta Dao-rajapinta käsittelee kirjautumisen SQLiten kautta metodilla ```loginSuccess()```. Tämä palauttaa totuusarvon LoginSceneControllerille. Jos vastaus oli true, niin LoginSceneController välittää Ui:lle asetettavaksi aloitusnäkymän metodilla ```setIntroScene```. Näkymä siirtyy aloitusnäkymään. 

<img src ="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kirjautuminen-sekvenssikaavio.png">

### Varsinaisen matopeli-näkymän käynnistyminen

Ui-luokan alustuksen jälkeen, introScene-näkymässä olevan "Aloita peli :)" -nappia painatteassa introController-luokan ``` handleInitGame()```  -metodin kautta Ui asettaa näkymäksi``` gameScenen``` eli varsinaisen pelinäkymän ```setGameScene()```-metodin kautta. GameOverSceneController alustaa GameWindow:n luoman pelinäkymän Ui:lle. Lisäksi GameWindow saa GameOverSceneController-luokan injektion, jonka avulla GameWindow voi käsitellä pelin loppumista metodilla ```handleGameOver()```.

GameWindow-luokka alustaa SnakeClassin ja Food-luokkien kautta pelille tarpeelliset Oliot ja pelinäkymän ``` initializeSnake()```, ```initializeScore()``` ja ``` initializeRootpane()``` -metodien kautta. Peli käynnistetään ```startGame()```- metodin kautta ja lisätään ohjaustoiminto ```addKeyListener()``` -metodin kautta. Nyt vihdoinkin on rakennettu pelinäkymä ```gameScene```, joka palautetaan Ui-luokalle ja tämä asettaa pelinäkymän käyttäjälle. 

Peli päivittyy jatkuvasti ``` render() ``` -metodin kautta, jolloin SnakeClass-olion kautta voidaan säätää maton nopeus, pituus ja suunta. Pelin loppuessa GameWindow kutsuu GameOverSceneController-luokan ```handleGameOver()```-metodia, jolloin Ui-luokka asettaa ```setGameOverScene```-metodin kautta pelin loppunäkymän. 


<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/matopeli-sekvenssikaavio.png">
