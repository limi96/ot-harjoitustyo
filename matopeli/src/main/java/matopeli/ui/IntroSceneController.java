package matopeli.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;


/**
 * Aloitus-näkymän toimintoja käsittelevä luokka
 */

public class IntroSceneController implements Initializable {

    private Ui application; 

    /**
     * Taustan värivaihtoehtojen käsittelijä
     */
    @FXML
    public RadioButton blackRadio;
    
    @FXML
    public RadioButton whiteRadio;

    /**
     * Madon värivaihtoehdon käsittelijä
     */
    @FXML
    public ColorPicker snakePickColor; 

    /**
     * Madon värivaihtoehdon käsittelijä
     */
    @FXML
    public ColorPicker foodPickColor; 


    public void setApplication(Ui application) {
        this.application = application; 
    
    }

    /**
     * Käynnistää pelin ja hakee halutut värivaihtoehdot
     */
 
    @FXML
    private void handleInitGame(ActionEvent event) {
        boolean backgroundIsBlack = blackRadio.isSelected() ? true : false;  
        Color snakeColor = snakePickColor.getValue(); 
        Color foodColor = foodPickColor.getValue(); 

        application.setGameWindow(snakeColor, foodColor, backgroundIsBlack);
    }

    @FXML
    public void handleToHighScores(ActionEvent event) throws Exception {
        application.setHighScoresScene();
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        application.setLoginScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
}
