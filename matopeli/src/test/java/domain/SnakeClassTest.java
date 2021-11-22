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
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color; 
import matopeli.domain.SnakeClass;
import matopeli.domain.Direction;

/**
 *
 * @author limi
 */
public class SnakeClassTest {
    
    Pane root;
    Circle head;
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
        root.setPrefHeight(WINDOW_HEIGHT);
        root.setPrefWidth(WINDOW_WIDTH);
        head = new Circle(WINDOW_WIDTH/2, WINDOW_HEIGHT/2, UNIT_SIZE/2);
        snake = new SnakeClass(head); 
        root.getChildren().add(snake.getSnakeHead());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void startingDirection() {
        System.out.println(snake.getDirection());
        
        // assertEquals(snake.getDirection(), Direction.RIGHT);
    }

}
