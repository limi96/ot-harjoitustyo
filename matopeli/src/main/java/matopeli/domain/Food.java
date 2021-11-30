package matopeli.domain;

import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Food {
    
    Random rnd = new Random(); 

    private Rectangle food; 

    public Food(Rectangle food) {
        this.food = food; 
        this.food.setFill(Color.RED);
        randomPosition();
    }

    public void randomPosition() {
        this.food.setTranslateX(30 + rnd.nextInt(550));
        this.food.setTranslateY(30 + rnd.nextInt(550));
    }

    public Rectangle getFood() {
        return this.food; 
    }

}
