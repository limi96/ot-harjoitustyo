/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color; 

import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;
import matopeli.domain.Food;


/**
 *
 * @author limi
 */
public class SnakeClassTest {
    
    Pane root;
    Rectangle head;
    SnakeClass snake;
    
    public SnakeClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
        
    @Before
    public void setUp() {
        int UNIT_SIZE = 20; 
        int WINDOW_HEIGHT = 30*UNIT_SIZE;
        int WINDOW_WIDTH  = 30*UNIT_SIZE;
        root = new Pane(); 
        root.setPrefHeight(WINDOW_HEIGHT);
        root.setPrefWidth(WINDOW_WIDTH);
        head = new Rectangle(20, 20);
        snake = new SnakeClass(head, new Group()); 
        root.getChildren().add(this.snake.getSnakeHead());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void startingDirection() {        
        assertEquals(snake.getDirection(), Direction.RIGHT);
    }
    
    @Test
    public void allDirectionsWork() {

        snake.setDirection(Direction.RIGHT);
        assertEquals(snake.getDirection(), Direction.RIGHT);
        snake.setDirection(Direction.LEFT);
        assertEquals(snake.getDirection(), Direction.LEFT);
        snake.setDirection(Direction.UP);
        assertEquals(snake.getDirection(), Direction.UP);
        snake.setDirection(Direction.DOWN);
        assertEquals(snake.getDirection(), Direction.DOWN);
        
    }
    
    @Test
    public void collisionTests() {
        
        snake.getSnakeHead().setTranslateX(20);
        snake.getSnakeHead().setTranslateY(20);   
        assertEquals(snake.collideWithSelf(), false);
        
        Rectangle newPart = new Rectangle(20,20);
        newPart.setTranslateX(20);
        newPart.setTranslateY(20);        
        snake.getSnakeBody().add(newPart);
        
        assertEquals(snake.collideWithSelf(), true);
        assertEquals(snake.collideWithBorder(), false);
        
        snake.getSnakeHead().setTranslateX(-1000);
        assertEquals(snake.collideWithBorder(), true);
        snake.getSnakeHead().setTranslateX(2000);
        assertEquals(snake.collideWithBorder(), true);
        snake.getSnakeHead().setTranslateX(-800);
        snake.getSnakeHead().setTranslateY(1000);
        assertEquals(snake.collideWithBorder(), true);
        snake.getSnakeHead().setTranslateY(-800);
        assertEquals(snake.collideWithBorder(), true);
    }
    
    @Test
    public void increaseSpeedMakesSnakeFaster() {
        
        //First increase speed to maximum of 20
        for (int i = 0; i < 65; i++) {
            snake.increaseSpeed();
        }
        
        int oldY = (int) snake.getSnakeHead().getTranslateY();
        int oldX = (int) snake.getSnakeHead().getTranslateX();

        snake.updateDirection();

        int newY = (int) snake.getSnakeHead().getTranslateY();
        int newX = (int) snake.getSnakeHead().getTranslateX();

        assertEquals(oldY - newY, 0);
        assertEquals(oldX - newX, -20);
    }
    
    @Test
    public void collisionWithFood() {
        
        Food food = new Food(new Rectangle(20, 20)); 
        food.randomPosition();         
        snake.getSnakeHead().setTranslateX(food.getFood().getTranslateX() - 20);
        snake.getSnakeHead().setTranslateY(food.getFood().getTranslateY() + 20);
        
        assertEquals(snake.touchesFood(food), true);
        snake.getSnakeHead().setTranslateX(10);
        snake.getSnakeHead().setTranslateY(10);
        assertEquals(snake.touchesFood(food), false);
        
    }
    
    @Test
    public void allMovementsWork() {

        int oldY = (int) snake.getSnakeHead().getTranslateY();
        int oldX = (int) snake.getSnakeHead().getTranslateX();

        snake.setDirection(Direction.UP);

        for (int i = 0; i < 6; i++) { 
            snake.updateDirection();
        }
        snake.setDirection(Direction.DOWN);
        snake.updateDirection();

        snake.setDirection(Direction.LEFT);

        for (int i = 0; i < 6; i++) { 
            snake.updateDirection();
        }

        snake.setDirection(Direction.RIGHT);
        snake.updateDirection();

        int newY = (int) snake.getSnakeHead().getTranslateY();
        int newX = (int) snake.getSnakeHead().getTranslateX();

        assertEquals(oldY - newY, 10);
        assertEquals(oldX - newX, 10);
    }
}
