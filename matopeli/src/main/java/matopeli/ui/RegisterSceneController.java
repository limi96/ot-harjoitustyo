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
 * Rekisteröinti-näkymän toimintoja käsittelevä luokka
 */

public class RegisterSceneController implements Initializable {
    private Ui application; 
    private UserDao user; 
    @FXML
    public TextField usernameField; 
    @FXML
    public PasswordField passwordField; 
    @FXML
    public PasswordField passwordRepeatField; 
    @FXML
    public Label uiMessage; 

    
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
    public void handleRegistration(ActionEvent event) {

        uiMessage.setText("");

        String username = usernameField.getText().toLowerCase(); 
        String password = passwordField.getText().toLowerCase(); 
        String passwordRepeat = passwordRepeatField.getText().toLowerCase(); 

        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
        System.out.println("Repeated : " + passwordRepeat);

        if (!password.equals(passwordRepeat)) {
            uiMessage.setText("Salasanat eivät täsmää!");
        } else {
            if (user.registerUser(username, password)) {
                uiMessage.setText("Rekisteröityminen onnistui!");
            }
        }
    }

    @FXML
    public void handleGoToLogin(ActionEvent event) {
        application.setLoginScene();
    }
    
    @FXML public void start() {
        
    }
}
