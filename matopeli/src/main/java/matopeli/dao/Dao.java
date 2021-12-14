package matopeli.dao;

import java.sql.SQLException;

public interface Dao {
    void initializeTable() throws SQLException;
    boolean registerUser(String username, String password);
    boolean loginSuccess(String username, String password); 
}
