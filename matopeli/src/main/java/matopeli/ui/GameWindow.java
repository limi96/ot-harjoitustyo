package matopeli.ui;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import matopeli.domain.Food;
import matopeli.domain.SnakeClass;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Group; 

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import matopeli.domain.Direction;

/**
 * Rakentaa varsinaisen matopeli-pelinäkymän.
 * Kaikki logiikka liittyy varsinaisen pelinäkymän luomiseen. 
 * Tarvitsee GameSceneControllerin, jotta voi olla Ui:hin yhteydessä ennen peliä ja sen jälkeen. 
 * 
 */
public class GameWindow {

    /**
    * Pelinäkymän ulottuvuksien säätely
    */

    public static int UNIT_SIZE = 20; 
    public static int WINDOW_HEIGHT = 40*UNIT_SIZE;
    public static int WINDOW_WIDTH  = 40*UNIT_SIZE;

    /**
    * Eri värivaihtoehtojen käsittely
    */
    public static Color snakeColor;
    public static Color foodColor;
    public static boolean backgroundIsBlack; 

    /**
    * Pelinäkymässä esiintyvien eri luokkien osat 
    */
    public static Food food; 
    public static SnakeClass snake;
    public static Group snakeGroup;
    /**
    * Tarkistaa onko peli päättynyt
    */  
    public static boolean gameOver;
    /**
    * Varsinainen näkyvillä pelipisteiden teksti
    */
    public static Text gameScore; 
    /**
    * Apuri, jolla voidaan dynaamisesti kasvattaa pisteitä 
    */
    public static AtomicInteger score;

    /**
    * Pelin käynnistyksen osat 
    */

    public static AnimationTimer gameTimer;
    public static long systemTime = System.nanoTime();

    /**
    * Pelin tauottamista käsittelevät osats
    */
    public static Label pause;
    public static boolean paused;

    /**
    * Pelinäkymän käsittelevät osat
    */
    public static Pane root; 
    public static Scene gameScene; 
    public static GameSceneController gameSceneController; 

    /**
     * Linkitetään GameSceneController
     */
    public GameWindow(GameSceneController controller) {
        gameSceneController = controller; 
    }
    
    /**
     * Alustetaan mato SnakeClass:in kautta ja ruoka Food-luokan kautta
     * @param snakeColorInput haluttu madon väri
     * @param foodColorInput haluttu ruuan väri 
     */
    public static void initializeSnake(Color snakeColorInput, Color foodColorInput) {
        snakeColor = snakeColorInput; 
        foodColor = foodColorInput; 
        Rectangle head = new Rectangle(UNIT_SIZE, UNIT_SIZE);
        head.setTranslateX(WINDOW_WIDTH / 2);
        head.setTranslateY(WINDOW_HEIGHT / 2);
        snakeGroup = new Group(); 

        checkSnakeAndFoodColors();

        snake = new SnakeClass(head, snakeGroup, snakeColor); 
        food = new Food(new Rectangle(UNIT_SIZE, UNIT_SIZE), foodColor); 
    }

    /**
     * Asettaa madon ja ruuan värit oletusarvoiksi, jos ne ovat samanvärisiä kuin jompikumpi tausta 
     */
    public static void checkSnakeAndFoodColors() {
        boolean checkIfBlackOrWhiteSnake = snakeColor.toString().equals("0xffffffff") || snakeColor.toString().equals("0x000000ff");
        boolean checkIfBlackOrWhiteFood = foodColor.toString().equals("0xffffffff") || foodColor.toString().equals("0x000000ff");
        snakeColor = checkIfBlackOrWhiteSnake ? Color.GREEN : snakeColor; 
        foodColor = checkIfBlackOrWhiteFood ? Color.RED : foodColor; 
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
    }

    /**
    * Asetetaan taustalle oikea väri vaihtoehdon mukaisesti
    * @param backgroundIsBlack katsoo onko väri musta vai ei (valkoinen muuten)
    */
    public static void setBackgroundColors(boolean backgroundIsBlack) {
        if (backgroundIsBlack) {
            root.setStyle("-fx-border-color: white;" + "-fx-background-color: black;");
            pause.setStyle(pause.getStyle() + "-fx-text-fill: black;" + "-fx-background-color: white;");
        } else {
            root.setStyle("-fx-background-color: white;");
            pause.setStyle(pause.getStyle() + "-fx-text-fill: white;" + "-fx-background-color: black;");
        }
    }

    /**
     * Alustetaan pisteytys -ja tauotustoiminnot
     */
    public static void initializeScoreAndPause() {
        pause = new Label(); 
        pause.setText("Peli tauolla");
        pause.setTranslateX(300);
        pause.setTranslateY(400); 
        pause.setStyle("-fx-font: 40 arial;");
        paused = false; 
        
        gameScore = new Text(0, 20, "Pisteet : 0"); 
        gameScore.setFill(Color.BLUE); 
        gameScore.setStyle("-fx-font: 20 arial;");
        score = new AtomicInteger();
    }
    /**
     * Rakentaa pelinäkymän ja palauttaa pelinäkymän GameOverControllerille, joka vie sen Ui:lle.
     * Parametrina ovat eri värivaihtoehdot, jotka valittiin Intro-näkymässä
     * @param snakeColorInput madon väri 
     * @param foodColorInput ruuan väri
     * @param backgroundIsBlackInput taustan väri
     */
    
     public Scene getGameScene(Color snakeColorInput, Color foodColorInput, boolean backgroundIsBlackInput) {

        snakeColor = snakeColorInput;
        foodColor = foodColorInput; 
        backgroundIsBlack = backgroundIsBlackInput; 
        
        initializeSnake(snakeColorInput, foodColorInput);
        initializeScoreAndPause(); 
        initializeRootPane();
        setBackgroundColors(backgroundIsBlackInput);

        gameScene = new Scene(root);
        addKeyListener(); 
        startGame();        
        
        return gameScene;
    }

    /**
     * Lisää näppäimistötoiminnot pelille 
     *
     */
    public static void addKeyListener() {

        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, pressedKey -> {
            if (!paused && (pressedKey.getCode() == KeyCode.W || pressedKey.getCode() == KeyCode.UP) && snake.getDirection() != Direction.UP && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
            if (!paused && (pressedKey.getCode() == KeyCode.S || pressedKey.getCode() == KeyCode.DOWN) && snake.getDirection() != Direction.DOWN && snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
            if (!paused && (pressedKey.getCode() == KeyCode.A || pressedKey.getCode() == KeyCode.LEFT) && snake.getDirection() != Direction.LEFT && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
            if (!paused && (pressedKey.getCode() == KeyCode.D || pressedKey.getCode() == KeyCode.RIGHT) && snake.getDirection() != Direction.RIGHT && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }            

            if (pressedKey.getCode() == KeyCode.P) {
                if (!paused) {
                    gameTimer.stop();
                    root.getChildren().add(pause);
                    paused = true; 
                } else {
                    gameTimer.start();
                    root.getChildren().remove(pause);
                    paused = false; 
                }
            }   
        });
    }

    /**
     * Käynnistetään peli AnimationTimer-luokan avulla. 
     * Peli päivittyy render()-metodin kautta. 
     */
    public static void startGame() {
        gameOver = false; 

        gameTimer = new AnimationTimer() {
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
            gameSceneController.handleGameOver(); 
            gameOver = true; 
        }
    }

    /**
     * Kasvatetaan pisteytystä 
     */
    public static void incrementScore() {
        gameScore.setText("Pisteet : " + score.addAndGet(100));
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public Color getFoodColor() {
        return foodColor;
    }

    public boolean getBackgroundIsBlack() {
        return backgroundIsBlack; 
    }

    public int getScore() {
        String[] scoreText = gameScore.getText().split(" "); 
        return Integer.parseInt(scoreText[2]); 
    }

}
