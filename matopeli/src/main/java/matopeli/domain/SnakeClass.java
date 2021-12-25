package matopeli.domain;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 
import javafx.collections.ObservableList; 
import javafx.scene.Group;

import java.lang.Math; 

/**
 * Luokka, joka toteuttaa kaikki matoon liittyvät toiminnot 
 */

public class SnakeClass {

    public Color snakeColor; 
/**
 *  Varsinainen "mato" toteutetaan listana.
 * Lista käsitellään Group-luokan kautta, jotta voidaan yhdistää Pane-luokkaan GameWindow-luokassa
 */

    public ObservableList snake; 

/**
 * Madon pää, jonka avulla mato liikkuu
 */
    public Rectangle head;
/**
 *  Madon vartalo, joka seuraa päätä
 */
    public Rectangle tail; 
/**
 * Madon tämänhetkinen suunta
 */
    public Direction direction = Direction.RIGHT;
/**
 * Madon tämänhetkinen nopeus
 */
    public double speed;  

/**
 * Madon konstruktori. Saa parametrikseen madon pään ja alustavan snakeGroup sekä madon halutun väri.
 * Käytössä on Group-luokka, jotta snake-lista pystytään liittämään GameWindow-luokan Pane-luokan root-paneeliin
 * 
 * @param head Madolle luotu pää
 * @param snakeGroup Madolle luotu tallennuslista, joka on kytköksissä GameWindow-luokkaan
 * @param snakeColor Ui:n asettama haluttu madon väri
 */
     
    public SnakeClass(Rectangle head, Group snakeGroup, Color snakeColor) {
        this.snake = snakeGroup.getChildren();
        this.head = head; 
        this.tail = head;          
        this.snakeColor = snakeColor; 
        this.snake.add(this.head); 
        this.speed = 2; 
        
        head.setFill(snakeColor);
        tail.setFill(snakeColor); 
        growSnake();
    }

/**
 * Kasvattaa madon nopeuden.
 * Nopeus kasvaa ensin paljon, mutta sitten nopeutuminen vähentyy
 */
    public void increaseSpeed() {
        this.speed += Math.pow(2, -this.speed / 2);
    }

/**
 * Määrittelee madon suunnan ja ottaa parametrikseen Direction-enumin arvon. 
 * Suunnan päivityessä kutsutaan updateDirection()-metodia, jotta mato alkaa
 * heti liikkumaan kyseiseen suuntaan
 * @param direction
 */
    public void setDirection(Direction direction) {
        this.direction = direction; 
        updateDirection();
    }   

    public Direction getDirection() {
        return this.direction; 
    }
/**
 * Kasvattaa madon asettemalla uusia neliötä osaksi snake ObservableList.listaa
 * Tämän jälkeen uusi madon osa laitetaan madon hännäksi. 
 */
    public void growSnake() {
        for (int i = 0; i < 50; i++) { 
            Rectangle newSnakePart = setNewPosition(getTail(), 0, 0);
            newSnakePart.setFill(snakeColor);
            getSnakeBody().add(newSnakePart);
            setTail(newSnakePart);
        }
    }
/**
 *  Tämä päivittää madon osan sijainnin.
 *  
 *  Madon osan sijainti päivittyy siten, että luodaan uusi samanlainen osa, joka on uudessa
 *  sijainnissa
 *  
 * @param snakePart päivitettävä osa
 * @param dx muutos X-akselin suuntaan
 * @param dy muutos Y-akselin suuntaan
 * @return palauttaa uuden neliön, joka on uudessa sijainnissa
 */
    public Rectangle setNewPosition(Rectangle snakePart, double dx, double dy) {
        Rectangle newSnakePart = new Rectangle(20, 20);
        double x = snakePart.getTranslateX() + dx;
        double y = snakePart.getTranslateY() + dy;

        newSnakePart.setTranslateX(x);
        newSnakePart.setTranslateY(y);
        newSnakePart.setFill(snakeColor);

        return newSnakePart;
    }
/**
 * Päivitetään madon sijainti nopeuden mukaisesti. 
 * Käyttää apunaan setNewPosition()-metodia, jotta madon sijainti päivittyy 
 * Suuntana käytetään Direction-luokan enum-arvoja, jotta tiedetään, mihin suuntaan pitää päivittää.
 * Madon sijainti päivitetään poistamalla häntä ja asettamalla uusi pää liikutettavaan suuntaan.
 * Tämä toistetaan jatkuvasti, jolloin häntä seuraa päätä. 
 */
    public void updateDirection() {

        for (int i = 0; i < this.speed; i++) { 
            snake.remove(snake.size() - 1);             
            double dy = 0;
            double dx = 0; 
    
            switch (this.direction) {
                case UP:
                    dy = -1;
                    break; 
                case DOWN:
                    dy = 1;
                    break; 
                case LEFT:
                    dx = -1;
                    break; 
                case RIGHT:
                    dx = 1;
                    break; 
            }
            
            Rectangle newTail = setNewPosition(getSnakeHead(), dx, dy);
            snake.add(0, newTail);
            this.head = (Rectangle) snake.get(0); 
         
        }
    }
/**
 * Tarkistaa, jos madon pää on osunut ruokaan 
 * @param food ruoka-olio
 * @return palauttaa totuusarvon, että osuiko mato ruokaan
 */
    public Boolean touchesFood(Food food) { 
 
        for (int i = 0; i < snake.size(); i++) { 

            Rectangle snakePart = (Rectangle) snake.get(i);
            double x = Math.abs(food.getFood().getTranslateX() - snakePart.getTranslateX());
            double y = Math.abs(food.getFood().getTranslateY() - snakePart.getTranslateY());

            if (x <= 20 && y <= 20) { 
                return true; 
            }

        }        
       
        return false; 
    }
/**
 * Tarkistaa, jos mato on osunut itseensä. 
 * Metodi tarkistaa jokaisen madon osan sijainnin ja sen, että
 * onko pään sijainti sama kuin jokin madon osista. Jos on, tiedetään, että mato on osunut itseensä. 
 * 
 * @return palauttaa, onko mato osunut itseensä 
 * 
 */
    public Boolean collideWithSelf() {

        for (int i = 1; i < snake.size(); i++) {     

            Rectangle snakePart = (Rectangle) snake.get(i);
            
            int partY = (int) snakePart.getTranslateY();
            int partX = (int) snakePart.getTranslateX();
            int headY = (int) getSnakeHead().getTranslateY();
            int headX = (int) getSnakeHead().getTranslateX();

            if (partY == headY && partX == headX) {        
                return true; 
            }
        }

        return false; 
    }
/**
 * Tarkistaa, jos mato on osunut pelinäkymän reunoihin.
 * @return totuusarvo tarkistukselle
 */
    public Boolean collideWithBorder() {

        double y = getSnakeHead().getTranslateY();
        double x = getSnakeHead().getTranslateX();

        if (y < 0 || x < 0 || y > 800 || x > 800) {
            return true;
        }
        return false; 
    }

    public Rectangle getSnakeHead() {
        return (Rectangle) this.snake.get(0); 
    }

    public ObservableList getSnakeBody() {
        return snake; 
    }

    public void setTail(Rectangle tail) {
        this.tail = tail; 
    }

    public Rectangle getTail() {
        return (Rectangle) snake.get(getSnakeBody().size() - 1);
    }    
}

