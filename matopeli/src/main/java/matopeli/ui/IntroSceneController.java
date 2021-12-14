package matopeli.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class IntroSceneController implements Initializable {

    private Ui application; 

    public void setApplication(Ui application) {
        this.application = application; 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleInitGame(ActionEvent event) {
        application.setGameWindow();
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        application.setLoginScene();
    }

    @FXML public void start() {
        
    }


}
