package matopeli.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import matopeli.dao.UserDao;


/**
 * Kirjautumis-näkymän toimintoja käsittelevä luokka
 */


public class LoginSceneController implements Initializable {

    private Ui application; 
    private UserDao user; 
    @FXML
    public TextField usernameField; 
    @FXML
    public PasswordField passwordField; 
    @FXML
    public Label errorMessage; 

    public void setApplication(Ui application) {
        this.application = application; 
    }
/**
* Aloittaa konfiguroinnin lataamalla tietokannan käyttäen config.properties-tiedostoa
* 
*
* @param url url
* @param rb  ResourceBundle
*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start();
        Properties property = new Properties(); 
        
        try {
            property.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Failed to load Config.properties");
        }

        String databaseURL = property.getProperty("databaseURL");  

        try {
            user = new UserDao(databaseURL);
        } catch (Exception e) {
            System.out.println("Could not load database!");
        }
    }    

    @FXML
    public void handleLogin(ActionEvent event) {

        String username = usernameField.getText().toLowerCase(); 
        String password = passwordField.getText().toLowerCase(); 
        
        if (user.loginSuccess(username, password)) {
            application.setIntroScene();
        } else {
            errorMessage.setText("Kirjautuminen epäonnistui!"); 
        }
    }

    @FXML
    public void handleGoToIntro(ActionEvent event) {
        application.setIntroScene();
    }
    
    @FXML
    public void handleGoToRegister(ActionEvent event) {
        application.setRegisterScene();
    }

    @FXML public void start() {
        
    }
}
