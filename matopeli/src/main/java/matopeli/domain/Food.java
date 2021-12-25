package matopeli.domain;

import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


/**
 * Luokka, joka toteuttaa ruoka-olion matopelissä 
 */
public class Food {
    
/**
 * Käytetään satunnaisen uuden sijainnin luomiseen 
 */
    Random rnd = new Random(); 
    private Rectangle food; 

/**
 * Alustetaan ruoka olio Rectangle-luokan avulla
 * Samalla asetetaan heti satunnainen sijainti randomPosition()-metodin avulla
 * @param food SnakeClass:ilta saatu Rectangle-olio ruuan määrittelemiseksi.
 * @param foodColor ruualle valittu väri
 */
    public Food(Rectangle food, Color foodColor) {
        this.food = food; 
        this.food.setFill(foodColor);
        randomPosition();
    }
/**
 * Asetetaan satunnainen sijainti. 
 * Uuden sijainnin arvo on 30-550 yksikköä nykyisestä. 
 */
    public void randomPosition() {
        this.food.setTranslateX(30 + rnd.nextInt(550));
        this.food.setTranslateY(30 + rnd.nextInt(550));
    }

    public Rectangle getFood() {
        return this.food; 
    }

}
