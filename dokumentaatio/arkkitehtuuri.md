# Arkkitehtuuri

## Pakkauskaavio

<img src= "https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png">

## Sovelluslogiikka

<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sovelluslogiikka.png">

## Sekvenssikaaviot

### Käyttäjän rekisteröytyminen

Ui-luokka alustaa RegisterSceneController-luokan ja asettaa rekisteröytymis. Samalla RegisterSceneController luokka alustaa Dao-rajapinnan "User"-toimintoja varten. 

Kun käyttäjä painaa nappia "Rekisteröidy" RegisterSceneControllerin ```handleRegistration()``` funktion kautta Dao-rajapinta käsittelee rekisteröytymisen SQLiten kautta metodilla ```registerUser()```. Tämä palauttaa totuusarvon RegisterSceneControllerille. 
Käyttäjä voi painaa tämän jälkeen nappia "Takaisin", jolloin RegisterSceneController välittää Ui:lle asetettavaksi kirjautumisnäkymän metodilla ```setLoginScene```. Näkymä siirtyy kirjautumisnäkymään. 


### Käyttäjän luominen

Ui-luokka alustaa LoginSceneController-luokan ja asettaa kirjautumisnäkymän. Samalla LoginSceneController luokka alustaa Dao-rajapinnan "User"-toimintoja varten. 

Kun käyttäjä painaa nappia "Kirjaudu" LoginSceneControllerin ```handleLogin()``` funktion kautta Dao-rajapinta käsittelee kirjautumisen SQLiten kautta metodilla ```loginSuccess()```. Tämä palauttaa totuusarvon LoginSceneControllerille. Jos vastaus oli true, niin LoginSceneController välittää Ui:lle asetettavaksi aloitusnäkymän metodilla ```setIntroScene```. Näkymä siirtyy aloitusnäkymään. 


### Varsinaisen matopeli-näkymän käynnistyminen

Ui-luokan alustuksen jälkeen, introScene-näkymässä olevan "Aloita peli :)" -nappia painatteassa introController-luokan ``` handleInitGame()```  -metodin kautta Ui asettaa näkymäksi``` gameScenen``` eli varsinaisen pelinäkymän ```setGameScene()```-metodin kautta. GameOverSceneController alustaa GameWindow:n luoman pelinäkymän Ui:lle. Lisäksi GameWindow saa GameOverSceneController-luokan injektion, jonka avulla GameWindow voi käsitellä pelin loppumista metodilla ```handleGameOver()```.

GameWindow-luokka alustaa SnakeClassin ja Food-luokkien kautta pelille tarpeelliset Oliot ja pelinäkymän ``` initializeSnake()```, ```initializeScore()``` ja ``` initializeRootpane()``` -metodien kautta. Peli käynnistetään ```startGame()```- metodin kautta ja lisätään ohjaustoiminto ```addKeyListener()``` -metodin kautta. Nyt vihdoinkin on rakennettu pelinäkymä ```gameScene```, joka palautetaan Ui-luokalle ja tämä asettaa pelinäkymän käyttäjälle. 

Peli päivittyy jatkuvasti ``` render() ``` -metodin kautta, jolloin SnakeClass-olion kautta voidaan säätää maton nopeus, pituus ja suunta. Pelin loppuessa GameWindow kutsuu GameOverSceneController-luokan ```handleGameOver()```-metodia, jolloin Ui-luokka asettaa ```setGameOverScene```-metodin kautta pelin loppunäkymän. 


<img src="https://github.com/limi96/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.png">
