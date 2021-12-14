package matopeli.ui;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import matopeli.domain.Food;
import matopeli.domain.SnakeClass;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Group; 

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import matopeli.domain.Direction;

/**
 * Rakentaa varsinaisen matopeli-pelinäkymän 
 * Tarvitsee GameOverControllerin, jotta voi olla Ui:hin yhteydessä pelin päättymisen jälkeen. 
 * 
 */
public class GameWindow {

    public static int UNIT_SIZE = 20; 
    public static int WINDOW_HEIGHT = 40*UNIT_SIZE;
    public static int WINDOW_WIDTH  = 40*UNIT_SIZE;

    public static Food food; 
    public static SnakeClass snake;
    public static Group snakeGroup;  
    public static Text gameScore; 
    public static boolean gameOver;
    public static AtomicInteger score;
    
    
    public static long systemTime = System.nanoTime();
    
    public static Pane root; 
    public static Scene gameScene; 
    public static GameOverSceneController gameOverController; 

/**
 * Linkitetään GameOverController
 */

    public GameWindow(GameOverSceneController controller) {
        gameOverController = controller; 
    }
    
/**
 * Alustetaan mato SnakeClass:in kautta ja ruoka Food-luokan kautta
 */
    public static void initializeSnake() {
        Rectangle head = new Rectangle(UNIT_SIZE,UNIT_SIZE);
        head.setTranslateX(WINDOW_WIDTH/2);
        head.setTranslateY(WINDOW_HEIGHT/2);
        snakeGroup = new Group(); 
        snake = new SnakeClass(head, snakeGroup); 

        food = new Food(new Rectangle(UNIT_SIZE,UNIT_SIZE)); 
    }

/**
 * Alustetaan juuripaneeli, joka on pohjana varsinaiselle pelinäkymälle
 */

    public static void initializeRootPane() {
        root = new Pane();
        root.setPrefSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        root.getChildren().add(snakeGroup);
        root.getChildren().add(food.getFood());
        root.getChildren().add(gameScore);
        root.setStyle("-fx-border-color: black");
    }
/**
 * Alustetaan pisteytystoiminto
 */
    public static void initializeScore() {
        gameScore = new Text(0, 20, "Pisteet : 0"); 
        gameScore.setFill(Color.BLUE); 
        gameScore.setStyle("-fx-font: 20 arial;");
        score = new AtomicInteger();
    }
/**
 * Rakentaa pelinäkymän ja palauttaa pelinäkymän GameOverControllerille, joka vie sen Ui:lle
 */
    public Scene getGameScene() {
        
        initializeSnake();
        initializeScore(); 
        initializeRootPane();

        gameScene = new Scene(root);
        addKeyListener(); 
        startGame();        
        
        return gameScene;
    }

/**
 * Lisää näppäimistötoiminnot pelille 
 * Käytössä on myös O-nappula, jonka avulla voi kasvattaa ja nopeuttaa matoa ilman, että syö
 * Tämä on testaamista varten, jos haluaa nopeasti testata peliä. 
 */
    public static void addKeyListener() {

        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, pressedKey -> {
            if ((pressedKey.getCode() == KeyCode.W || pressedKey.getCode() == KeyCode.UP) && snake.getDirection() != Direction.UP && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
            if ((pressedKey.getCode() == KeyCode.S || pressedKey.getCode() == KeyCode.DOWN) && snake.getDirection() != Direction.DOWN && snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
            if ((pressedKey.getCode() == KeyCode.A || pressedKey.getCode() == KeyCode.LEFT) && snake.getDirection() != Direction.LEFT && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
            if ((pressedKey.getCode() == KeyCode.D || pressedKey.getCode() == KeyCode.RIGHT) && snake.getDirection() != Direction.RIGHT && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }

            
            if (pressedKey.getCode() == KeyCode.O) {
                snake.growSnake();
                snake.increaseSpeed();
            }
        });
    }
/**
 * Käynnistetään peli AnimationTimer-luokan avulla. 
 * Peli päivittyy render()-metodin kautta. 
 */
    public static void startGame() {
        gameOver = false; 

        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {    
                if (currentTime-systemTime > 1E7) {
                    render();
                    systemTime = currentTime;
                }
                if (gameOver) {
                    this.stop(); 
                }
            }
        };
        gameTimer.start(); 
    }

/**
 * Päivittää pelin tapahtumat. 
 * Jos häviää, välittää gameOverControllerille viestin, että peli pitää lopettaa.
 * Asettaa samalla gameOver = true, jotta startGame()-metodi pysäyttää AnimationTimerin ja peli päättyy. 
 */
    public static void render() {        
        
        snake.updateDirection();

        if (snake.touchesFood(food)) {
            food.randomPosition();   
            snake.growSnake();
            incrementScore();
            snake.increaseSpeed();
        }   

        if (snake.collideWithSelf() || snake.collideWithBorder()) {
            gameOverController.handleGameOver(); 
            gameOver = true; 
        }
    }
/**
 * Kasvatetaan pisteytystä 
 */
    public static void incrementScore() {
        gameScore.setText("Pisteet : " + score.addAndGet(100));
    }

}
