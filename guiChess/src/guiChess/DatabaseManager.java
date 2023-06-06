package guiChess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

/**
 * This class manages the database.
 * it does NOT manage loaded state, using data right from the database for use
 * in the program is difficult because of storage type resrictions, so it will
 * be avoided, translation is prefered.
 * @author qrq1356
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:chessDB;create=true";
    private static final String USERS_TABLE = "USERS", GAMES_TABLE = "GAMES", MOVES_TABLE = "MOVES";
    
    private Connection connection;
    /**
     * establishes a connection to the DB to used throughout. this is to prevent
     * making many re-connections each time we want to work with the database.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            System.err.println("DB CONNECT: " + ex.getMessage());
        }
    }
    /**
     * self explanatory, combined ensure tables methods as there's little reason
     * to use them separately.
     */
    public void initTables() {
        createUsersTable();
        createGamesTable();
        createMovesTable();
    }
    
    /**
     * ensures table users table exists by creating one if it doesn't already
     */
    public void createUsersTable() {
        try {
            // Check if the table already exists
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, USERS_TABLE, null);
            if (resultSet.next()) {
                System.out.println("DEBUG: "+USERS_TABLE+" Table already exists");
                return;
            } else {
                // Create the table if it doesn't exist
                String sql = "CREATE TABLE "+USERS_TABLE+
                    "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"+
                    "username VARCHAR(50) NOT NULL,"+
                    "wins INT DEFAULT 0,"+
                    "losses INT DEFAULT 0)";
                connection.createStatement().executeUpdate(sql);
            }
        } catch (SQLException ex) {
            System.err.println("CREATE TABLE USERS: " + ex.getMessage());
        }
    }
    public void createGamesTable() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, GAMES_TABLE, null);
            if (resultSet.next()) {
                System.out.println("DEBUG: "+GAMES_TABLE+" Table already exists");
                return;
            } else {
                String sql = "CREATE TABLE "+GAMES_TABLE+
                        "(ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"+
                        "Player1 INT REFERENCES "+USERS_TABLE+"(ID)"+
                        "Player2 INT REFERENCES "+USERS_TABLE+"(ID)"+
                        "Status BOOLEAN)";
                connection.createStatement().executeUpdate(sql);
            }
        } catch (SQLException ex) {
            System.err.println("CREATE TABLE GAMES: " + ex.getMessage());
        }
    }
    public void createMovesTable() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, MOVES_TABLE, null);
            if (resultSet.next()) {
                System.out.println("DEBUG: "+MOVES_TABLE+" Table already exists");
                return;
            } else {
                String sql = "CREATE TABLE "+MOVES_TABLE+
                        "(ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "+
                        "GameID INT REFERENCES "+GAMES_TABLE+"(ID),"+
                        "PlayerID INT REFERENCES "+USERS_TABLE+"(ID),"+
                        "FromSquare VARCHAR(2),"+
                        "ToSquare VARCHAR(2))";
                connection.createStatement().executeUpdate(sql);
            }
        } catch (SQLException ex) {
            System.err.println("CREATE TALBE MOVES: " + ex.getMessage());
        }
    }
    /**
     * attempts to add a user by name
     * @param username
     * @return -1 if username is taken, 0 on success, 1 on misc error.
     */
    public int addUser(String username) {
        try {
            PreparedStatement exists = connection.prepareStatement(
                "SELECT COUNT(*) FROM "+USERS_TABLE+" WHERE (username = ?)"
            );
            exists.setString(1, username);
            ResultSet resultSet = exists.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                return -1;
            } else {
                PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO "+USERS_TABLE+" (username) VALUES (?)"
                );
                insert.setString(1, username);
                insert.executeUpdate();
                return 0;
            }
        } catch (SQLException ex) {
            System.err.println("CREATE USER: " + ex.getMessage());
            return 1;
        }
    }
    /**
     * increments a users win count
     * @param username 
     * @return -1 on failure, 0 on success, 1 on misc failure
     */
    public int incrementWins(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE "+USERS_TABLE+" SET wins = wins + 1 WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return 0;
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.err.println("MOD USER: " + ex.getMessage());
            return 1;
        }
    }
    /**
     * increments a users loss count
     * @param username
     * @return -1 on failure, 0 on success, 1 on misc failure
     */
    public int incrementLosses(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE "+USERS_TABLE+" SET losses = losses + 1 WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return 0;
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.err.println("MOD USER: " + ex.getMessage());
            return 1;
        }
    }
    /**
     * creates a list of string representations of the users in the table.
     * this is useful for JList objects as getting this data shouldn't 
     * be their job.
     * @return users as a list of strings. as their usernames.
     */
    public DefaultListModel<String> getUsersNamesList() {
        DefaultListModel<String> userList = new DefaultListModel<>();
        try {
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT username FROM "+USERS_TABLE
            ); ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("username");
                    userList.addElement(name);
                }
            }
        } catch (SQLException ex) {
            System.err.println("GET USER LIST: " + ex.getMessage());
        }
        return userList;
    }
}
