package matopeli.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import matopeli.dao.Dao;

/**
 * Pelin loppumista käsittelevä luokka
 * Käsittelee pääasiassa pelin loppumista, uudelleenkäynnistämistä ja pisteiden hallintaa 
 * Alustaa myös gameWindow-luokka Ui:lle, jotta Ui saa varsinaisen pelinäkymän
 */

public class GameSceneController implements Initializable {

    private Ui application; 
    /**
     * Varsinainen pelinäkymä, joka välitetään Ui:lle
     */
    public GameWindow gameWindow; 
    /**
    * Kirjautumisessa käytetty DAO-rajapintaluokan välittäjä
    */
    private LoginSceneController loginController; 
    /**
    * DAO-rajapinta, joka käsittelee käyttäjän pisteiden päivittämisen
    */
    public Dao dao; 

    @FXML
    public Label scoreLabel; 

    /**
     * Yhdistää käyttöliittymään ja hakee loginControllerilta DAO-rajapinnan
     * 
     * @param application
     * @param loginController
     */

    public void setApplication(Ui application, LoginSceneController loginController) {
        this.application = application; 
        this.loginController = loginController; 
        dao = loginController.getDao(); 
    }

    /**
     * Päätetään peli, päivitetään pisteet ja lopulta vaihdetaan näkymä pelin päätenäkymäksi.
     */
    @FXML
    public void handleGameOver()  {
        int finalScore = gameWindow.getScore(); 
        String username = loginController.getUsername();   
        try {
            dao.setNewScore(username, finalScore);
        } catch (Exception e) {

        }
        scoreLabel.setText("Pisteesi : " + finalScore); 
        application.setGameOverScene();
    }
    /**
     * Pelin uudelleenkäynnistäminen samoilla väreillä
     */
    @FXML
    private void handleRestartGame(ActionEvent event) {
        application.setGameWindow(gameWindow.getSnakeColor(), gameWindow.getFoodColor(), gameWindow.getBackgroundIsBlack());
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        application.setLoginScene();
    }

    @FXML
    public void handleToHighScores(ActionEvent event) {
        application.setHighScoresScene();
    }

    @FXML
    public void handleGoToIntro(ActionEvent event) {
        application.setIntroScene();
    }
    
    public GameWindow getGameWindow() {
        return gameWindow; 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start();
    } 

    /**
     * Linkittää gameWindow-pelinäkymälle GameSceneControllerin, jotta tämä voi käsitellä pelin päättymisen
     */
    @FXML public void start() {
        gameWindow = new GameWindow(this);
    }
}
