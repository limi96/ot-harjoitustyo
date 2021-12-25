package matopeli.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Toteuttaa toiminnot Dao-rajapinnalle
 */
public class UserDao implements Dao {
/**
 * Käytetään tietokanta-yhteyden luomiseen
 */
    private Connection database; 
/**
 * Käytetään SQL-komentojen luomiseen
 */
    private PreparedStatement statement;

/**
 * Luodaan tietokantayhteys muodostamalla databaseURL:n määrittelemä db.tiedosto
 * Tämä tehdään DriverManager-luokan getConnection()-metodin kautta
 * Sitten alustetaan tietokanta initializeTable()-metodin kautta 
 * @param databaseURL osoite, johon luodaan tietokantayhteys
 */

    public UserDao(String databaseURL) throws Exception {
        database = DriverManager.getConnection(databaseURL);
        this.initializeTable();
    }

    public Connection getDatabase() {
        return database; 
    }

    @Override
    public void initializeTable() throws Exception {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT, score INTEGER)";
        statement = database.prepareStatement(sqlCommand);
        statement.executeUpdate();
        statement.close();
    }
    
    @Override
    public boolean registerUser(String username, String password) throws Exception {
        boolean success = false; 

        if (!checkUserExists(username)) {
            String sqlCommand = "INSERT INTO users (username, password, score) VALUES (?,?,0)";
            statement = database.prepareStatement(sqlCommand);
            statement.setString(1, username); 
            statement.setString(2, password); 
            success = statement.executeUpdate() > 0;
            statement.close();
        } else {
            return false; 
        }
        
        return success; 
    }
    @Override
    public boolean checkUserExists(String username) throws Exception {
        boolean success = false; 

        String sqlCommand = "SELECT * FROM users WHERE username =?";
        statement = database.prepareStatement(sqlCommand);
        statement.setString(1, username); 
        
        ResultSet results = statement.executeQuery();
        success = results.next(); 
        statement.close();

        return success; 
    }

    @Override
    public void setNewScore(String username, int score) throws Exception {

        String sqlCommand = "UPDATE users as U SET score = MAX(?, (SELECT score FROM users WHERE username =?)) WHERE U.username =?";
        statement = database.prepareStatement(sqlCommand);
        statement.setInt(1, score);
        statement.setString(2, username); 
        statement.setString(3, username); 
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public ArrayList<String> fetchHighScores() throws Exception {
        ArrayList<String> resultsList = new ArrayList<>(); 
        String sqlCommand = "SELECT username, score FROM users ORDER BY score DESC LIMIT 5";
        statement = database.prepareStatement(sqlCommand);            
        ResultSet results = statement.executeQuery();
                    

        while (results.next()) {
            resultsList.add(results.getString(1) + "," + results.getString(2));
        }

        statement.close();
        
        return resultsList; 
    }

    @Override
    public boolean loginSuccess(String username, String password) throws Exception {

        boolean success = false; 
        String sqlCommand = "SELECT * FROM users WHERE username =? AND password =?";
        statement = database.prepareStatement(sqlCommand);
        statement.setString(1, username); 
        statement.setString(2, password); 
        
        ResultSet results = statement.executeQuery();
        success = results.next(); 
        statement.close();

        return success; 
    }
    


}
