package matopeli.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import matopeli.dao.Dao;

/**
 * Rekisteröinti-näkymän toimintoja käsittelevä luokka
 */

public class RegisterSceneController implements Initializable {
    private Ui application; 

    /**
     * DAO-rajapinta rekisteröimistä varten
     */
    private Dao dao; 
    
    @FXML
    public TextField usernameField; 
    
    @FXML
    public PasswordField passwordField; 
    
    @FXML
    public PasswordField passwordRepeatField; 
    
    @FXML
    public Label uiMessage; 

    /**
     * Yhdistää käyttöliittymään ja hakee loginControllerilta DAO-rajapinnan
     * 
     * @param application
     * @param loginController
     */
    public void setApplication(Ui application, LoginSceneController loginController) {
        this.application = application; 
        dao = loginController.getDao();
        
    }

    /**
     * Käsittelee rekisteröimistä DAO-rajapinnan kautta
     * @param event
     * @throws Exception
     */
    @FXML
    public void handleRegistration(ActionEvent event) throws Exception {

        uiMessage.setText("");

        String username = usernameField.getText().toLowerCase(); 
        String password = passwordField.getText().toLowerCase(); 
        String passwordRepeat = passwordRepeatField.getText().toLowerCase(); 

        if (!password.equals(passwordRepeat)) {
            uiMessage.setText("Salasanat eivät täsmää!");
        } else {

            if (username.contains(" ") || password.contains(" ") || username.length() == 0 || password.length() == 0) {
                uiMessage.setText("Syötteessä ei saa olla välilyöntejä ja syötteet eivät saa olla tyhjiä!!");
            }

            else if (username.length() > 5) {
                uiMessage.setText("Nimi ei saa olla pitempi kuin 5 kirjainta!"); 
            }

            else if (dao.registerUser(username, password)) {
                uiMessage.setText("Rekisteröityminen onnistui!");
            }
            else {
                uiMessage.setText("Käyttäjätunnus on jo olemassa!");
            }
        }
    }

    @FXML
    public void handleGoToLogin(ActionEvent event) {
        application.setLoginScene();
        uiMessage.setText(""); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }  

    @FXML 
    public void start() throws Exception {
             
    }

}
