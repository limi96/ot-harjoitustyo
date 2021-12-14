package ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import matopeli.dao.UserDao; 

public class UserDaoTest {
    
    UserDao user; 
    
    public UserDaoTest() {
        
        Properties property = new Properties(); 
        
        try {
            property.load(new FileInputStream("config.properties"));
        }
        catch (IOException e) {
            System.out.println("Failed to load Config.properties");
        }

        String databaseURL = property.getProperty("testDatabaseURL");  

        try {
            user = new UserDao(databaseURL);
        }
        catch (Exception e) {
            System.out.println("Could not load database!");
        }
        
        
    }
    
    @Before
    public void setUp() {
        try {
            user.initializeTable();
            user.registerUser("1234","1234");
        }
        catch (Exception e) {
            System.out.println("setUp error : " + e);
        }
        
    }
    
    @After
    public void TearDown() {
        try {
        PreparedStatement statement; 
        String sqlCommand = "DROP TABLE IF EXISTS users";
        statement = user.getDatabase().prepareStatement(sqlCommand);
        statement.executeUpdate();
        statement.close();
        }
        catch (Exception e) {
            System.out.println("TearDown error : " + e);
        }
    }
    
    @Test
    public void initializeTableWorks() {
        boolean result = false; 
        
        try {
            user.initializeTable();
            result = true; 
        }
        catch(Exception e) {
            result = false; 
        }
        
        assertEquals(true, result);
    }

    @Test
    public void registerNewWorks() {
        boolean result = user.registerUser("kalle","kalle");
        assertEquals(true, result); 
    }
    
    @Test
    public void registerSameFails() {
        boolean result = user.registerUser("1234","12345");
        assertEquals(false, result);
    
    }
    
    @Test
    public void loginCorrectWorks() {
        boolean result = user.loginSuccess("1234", "1234");
        assertEquals(true, result);
    }
    @Test
    public void loginIncorrectWorks() {
        boolean result = user.loginSuccess("1234","12345");
        assertEquals(false, result);
        
        result = user.loginSuccess("12345","1234");
        assertEquals(false, result);
    }
        
}
