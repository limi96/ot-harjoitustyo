package matopeli.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GameOverSceneController implements Initializable {
    private Ui application; 
    public GameWindow gameWindow; 

    public void setApplication(Ui application) {
        this.application = application; 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start();
    }    

    @FXML
    public void handleGameOver() {
        application.setGameOverScene();
    }

    @FXML
    private void handleRestartGame(ActionEvent event) {
        application.setGameWindow();
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        application.setLoginScene();
    }
    
    public GameWindow getGameWindow() {
        return gameWindow; 
    }

    @FXML public void start() {
        gameWindow = new GameWindow(this);
    }
}
