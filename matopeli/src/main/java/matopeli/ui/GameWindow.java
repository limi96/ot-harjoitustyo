package matopeli.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;
import matopeli.domain.Food;


public class GameWindow extends Application {

    public static int UNIT_SIZE = 20; 
    public static int WINDOW_HEIGHT = 30*UNIT_SIZE;
    public static int WINDOW_WIDTH  = 30*UNIT_SIZE;

    long systemTime = System.nanoTime();

    @Override
    public void start(Stage window) {
        window.setTitle("Matopeli");

        Pane root = new Pane();
        root.setPrefHeight(WINDOW_HEIGHT);
        root.setPrefWidth(WINDOW_WIDTH);

        Circle head = new Circle(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, UNIT_SIZE/2);
        SnakeClass snake = new SnakeClass(head); 
        
        Food food = new Food(new Rectangle(UNIT_SIZE,UNIT_SIZE)); 

        root.getChildren().add(snake.getSnakeHead());
        root.getChildren().add(food.getFood());

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {

                // MAKE A RENDER THAT HANDLES ALL THE CHANGES                
                // public void render()

                if (snake.touchesFood(food)) {

                    food.randomPosition();

                    double x = snake.getTail().getCenterX()-20;
                    double y = snake.getTail().getCenterY()-20;

                    Circle newPart = new Circle(x,y, UNIT_SIZE/2);
                
                    newPart.setFill(Color.PINK);
                    snake.getSnakeBody().add(newPart);
                    root.getChildren().add(newPart);
                }
    
                if (currentTime-systemTime > 1E7) {
                    snake.updateDirection();
                    systemTime = currentTime;
                }
            
            }
            
        }.start();

        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK); 
    
        scene.addEventFilter(KeyEvent.KEY_PRESSED, pressedKey -> {
            if (pressedKey.getCode() == KeyCode.W && snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.UP);
            }
            if (pressedKey.getCode() == KeyCode.S && snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.DOWN);
            }
            if (pressedKey.getCode() == KeyCode.A && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.LEFT);
            }
            if (pressedKey.getCode() == KeyCode.D && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.RIGHT);
            }
        });

        window.setScene(scene);
        window.show();
    }

    

    public static void main(String[] args) {
        launch(GameWindow.class);
    }

    



}
