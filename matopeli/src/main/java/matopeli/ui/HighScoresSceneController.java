package matopeli.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import matopeli.dao.Dao;

/**
 * Pistetaulu-näkymän toimintoja käsittelevä luokka
 * Saa loginSceneControllerilta dao-käsittelijän
 * LoginSceneControllerin dao-käsittelijän kautta saadaan myös pisteet
 */

public class HighScoresSceneController implements Initializable {

    private Ui application; 

    /**
     * DAO-rajapinta, joka hakee korkeimmat pisteet
     */
    public Dao dao; 

    /**
     * Tuo pisteet näkyville
     */
    @FXML
    public Label scoreLabel; 
    /**
     * Tuo nimet näkyville
     */
    @FXML
    public Label nameLabel; 

    /**
     * Hae loginControllerilta alussa luotu DAO-rajapinta
     * start() kautta asettaa tulostaulun näkyville
     * @param application Linkitetty Ui-käsittelijä
     * @param loginController Linkitetty loginController, jolta saa DAO-käsittelijän
     * @throws Exception 
     */
    
    public void setApplication(Ui application, LoginSceneController loginController) throws Exception {
        this.application = application; 
        dao = loginController.getDao(); 
        start();
    }

    @FXML
    public void handleGoToIntro(ActionEvent event) {
        application.setIntroScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 

    /**
     * Haetaan korkeimmat pisteet ja laitetaan tulokset näkyville
     * @throws Exception 
     */

    @FXML 
    public void start() throws Exception {

        ArrayList<String> results = dao.fetchHighScores(); 
    
        String nameText = "Nimi \n\n";
        String scoreText = "Pisteet \n\n";

        for (String result : results) {
            String[] resultSplit = result.split(","); 
            nameText += resultSplit[0] + "\n"; 
            scoreText += resultSplit[1] + "\n"; 
        }

        nameLabel.setText(nameText);
        scoreLabel.setText(scoreText);
    }
    
   
}
