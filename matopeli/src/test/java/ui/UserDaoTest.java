package ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.ArrayList;
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
            user.registerUser("omena","omena");
        } catch (Exception e) {
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
        } catch (Exception e) {
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
    public void registerNewWorks() throws Exception {
        boolean result = user.registerUser("kalle","kalle");
        assertEquals(true, result); 
    }
    
    @Test
    public void checkIfUserExists() throws Exception {
        boolean result = user.checkUserExists("1234");
        assertEquals(true, result); 
    }

    @Test
    public void checkIfUserNotExists() throws Exception {
        boolean result = user.checkUserExists("joni");
        assertEquals(false, result); 
    }

    @Test
    public void registerSameFails() throws Exception {
        boolean result = user.registerUser("1234","1234");
        assertEquals(false, result);
    }
    
    @Test
    public void loginCorrectWorks() throws Exception {
        boolean result = user.loginSuccess("1234", "1234");
        assertEquals(true, result);
    }
    @Test
    public void loginIncorrectWorks() throws Exception {
        boolean result = user.loginSuccess("1234","12345");
        assertEquals(false, result);
        result = user.loginSuccess("12345","1234");
        assertEquals(false, result);
    }

    @Test
    public void setNewScoreTest() throws Exception {
        user.setNewScore("1234", 1000);
        ArrayList<String> scoreList = user.fetchHighScores(); 
        String[] resultSplit = scoreList.get(0).split(",");

        assertEquals("1234", resultSplit[0]);
        assertEquals("1000", resultSplit[1]);

        String[] resultSplitSecond = scoreList.get(1).split(",");
        assertEquals("omena", resultSplitSecond[0]);
        assertEquals("0", resultSplitSecond[1]);
    }

    @Test
    public void setNewScoreOnlyIfHigher() throws Exception {
        user.setNewScore("1234", 1000);
        user.setNewScore("1234", 500); 
    
        ArrayList<String> scoreList = user.fetchHighScores(); 
        String[] resultSplit = scoreList.get(0).split(",");
        assertEquals("1000", resultSplit[1]);

        user.setNewScore("1234", 1500); 
        scoreList = user.fetchHighScores(); 
        resultSplit = scoreList.get(0).split(",");
        assertEquals("1500", resultSplit[1]);
    }
        
}
