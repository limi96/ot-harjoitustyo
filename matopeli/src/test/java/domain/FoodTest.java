package domain;

import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import matopeli.domain.Food;
import matopeli.ui.GameWindow;

public class FoodTest {
    
    @Test 
    public void uiSetsCorrectColors() throws Exception {    
        GameWindow.initializeSnake(Color.PINK, Color.PINK);
        assertEquals(Color.PINK, GameWindow.food.getFood().getFill());
    }

    @Test
    public void foodStaysWithinBoundaries() {

        Food testFoodClass = new Food(new Rectangle(20, 20), Color.BLUE); 

        boolean detectFailure = false; 

        for (int i = 0; i < 10000; i++) { 
            testFoodClass.randomPosition();
            
            Rectangle food = testFoodClass.getFood(); 
            int x = (int) food.getTranslateX();
            int y = (int) food.getTranslateY();

            if (x < 0 || y < 0 || x > 800 || y > 800) {
                detectFailure = true; 
            }
        }

        assertEquals(false, detectFailure);

    }
}
