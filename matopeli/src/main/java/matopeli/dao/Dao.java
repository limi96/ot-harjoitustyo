package matopeli.dao;

import java.sql.SQLException;
/**
 * Rajapinta Dao-toiminnallisuuksille
 */
public interface Dao {
/**
 * rakentaa tietokannan, jos sitä ei ole vielä luotu
 */
    void initializeTable() throws SQLException;
/**
 * Rekisteröi käyttäjän 
 * @param username 
 * @param password
 * @return palauttaa totuusarvon sille, että rekisteröinti oli onnistunut
 */
    boolean registerUser(String username, String password);
/**
 * Kirjautuminen 
 * @param username 
 * @param password
 * @return palauttaa totuusarvon sille, että kirjautuminen oli onnistunut
 */
    boolean loginSuccess(String username, String password); 
}
