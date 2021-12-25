package matopeli.dao;

import java.util.ArrayList;

/**
 * Rajapinta Dao-toiminnallisuuksille
 */
public interface Dao {
/**
 * rakentaa tietokannan, jos sitä ei ole vielä luotu
 */
    void initializeTable() throws Exception;
/**
 * Rekisteröi käyttäjän 
 * @param username 
 * @param password
 * @return palauttaa totuusarvon sille, että rekisteröinti oli onnistunut
 */
    boolean registerUser(String username, String password) throws Exception;
/**
 * Kirjautuminen 
 * @param username 
 * @param password
 * @return palauttaa totuusarvon sille, että kirjautuminen oli onnistunut
 */
    boolean loginSuccess(String username, String password) throws Exception; 

    boolean checkUserExists(String username) throws Exception;

    void setNewScore(String username, int points) throws Exception;

    ArrayList<String> fetchHighScores() throws Exception;
}
