package matopeli.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao implements Dao {

    private Connection database; 
    private PreparedStatement statement;

    public UserDao(String databaseURL) throws SQLException {
        database = DriverManager.getConnection(databaseURL);
        this.initializeTable();
    }

    public Connection getDatabase() {
        return database; 
    }

    @Override
    public void initializeTable() throws SQLException {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (user_id SERIAL PRIMARY KEY, username TEXT UNIQUE, password TEXT, score INTEGER)";
        statement = database.prepareStatement(sqlCommand);
        statement.executeUpdate();
        statement.close();
    }
    
    @Override
    public boolean registerUser(String username, String password) {
        try {
            String sqlCommand = "INSERT INTO users (username, password) VALUES (?,?)";
            statement = database.prepareStatement(sqlCommand);
            statement.setString(1, username); 
            statement.setString(2, password); 
            boolean success = statement.executeUpdate() > 0; 
            statement.close();
            return success; 
        }

        catch(Exception e) {
            System.out.println("Registration error! " + e);
        }
        return false; 
    }

    @Override
    public boolean loginSuccess(String username, String password) {

        boolean success = false; 

        try {
            String sqlCommand = "SELECT * FROM users WHERE username =? AND password =?";
            statement = database.prepareStatement(sqlCommand);
            statement.setString(1, username); 
            statement.setString(2, password); 
            
            ResultSet results = statement.executeQuery();
            success = results.next(); 
            statement.close();
        }

        catch(Exception e) {
            System.out.println("Login Error! " + e);
        }

        return success; 
    }
    


}
