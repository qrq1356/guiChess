package guiChess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class manages DB management for Users.
 * it does NOT manage users themselves. this data should not be continously
 * read/written to. user state should always be internal. failure state 
 * handling is a requirement.
 * @author qrq1356
 */
public class UserDBManager {
    private static final String DB_URL = "jdbc:derby:userDB;create=true";
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
     * ensures table exists.
     */
    public void createTable() {
        try {
            // Check if the table already exists
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "Users", null);
            if (resultSet.next()) {
                System.out.println("Table already exists.");
                return;
            }
            // Create the table if it doesn't exist
            String sql = "CREATE TABLE Users (" +
                "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                "username VARCHAR(50) NOT NULL," +
                "wins INT DEFAULT 0," +
                "losses INT DEFAULT 0," +
                ")";
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("CREATE TABLE: " + ex.getMessage());
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
                "SELECT COUNT(*) FROM Users WHERE (username = ?)"
            );
            exists.setString(1, username);
            ResultSet resultSet = exists.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                return -1;
            } else {
                PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO Users (username) VALUES (?)"
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
                "UPDATE Users SET wins = wins + 1 WHERE username = ?"
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
                "UPDATE Users SET losses = losses + 1 WHERE username = ?"
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
}
