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

import matopeli.dao.UserDao;
import matopeli.dao.Dao;

/**
 * Kirjautumis-näkymän toimintoja käsittelevä luokka.
 * Alustaa myös DAO-rajapinnan käytön mahdolliseksi muille näkymien kontrollereille. 
 */

public class LoginSceneController implements Initializable {

    private Ui application; 
    /**
     * Kirjautumista käsittelevä DAO-rajapinta 
     */
    public Dao dao; 
    public String username; 
    public String password; 

    @FXML
    public TextField usernameField; 

    @FXML
    public PasswordField passwordField; 

    @FXML
    public Label errorMessage; 

    public void setApplication(Ui application) throws Exception {
        this.application = application; 
        start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    /**
     * Käsittelee kirjautumisen 
     * @throws Exception 
     */
    @FXML
    public void handleLogin(ActionEvent event) throws Exception {

        username = usernameField.getText().toLowerCase(); 
        password = passwordField.getText().toLowerCase(); 
        
        if (username.contains(" ") || password.contains(" ") || username.length() == 0 || password.length() == 0) {
            errorMessage.setText("Syötteessä ei saa olla välilyöntejä ja syötteet eivät saa olla tyhjiä!!"); 
        }
        else {
            if (dao.loginSuccess(username, password)) {
                application.setIntroScene();
            } else {
                errorMessage.setText("Kirjautuminen epäonnistui!"); 
            }
        }
    }
    
    @FXML
    public void handleGoToRegister(ActionEvent event) {
        application.setRegisterScene();
    }

    public String getUsername() {
        return username; 
    }

    public Dao getDao() {
        return dao; 
    }
    /**
     * Aloittaa konfiguroinnin.
     * Lataa config.properties tiedoston, jolla alustaa tietokannan
     * Luo ohjelmistolle DAO-rajapinnan, mitä muut näkymät käyttävät
     * @throws Exception
     */
    @FXML 
    public void start() throws Exception {
        Properties property = new Properties(); 
        property.load(new FileInputStream("config.properties"));
        
        String databaseURL = property.getProperty("databaseURL");  
        dao = new UserDao(databaseURL);
    
    }



}
