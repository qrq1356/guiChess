package guiChess;
// logging
import java.util.logging.Logger;
// database interface
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class manages interacting with the database, it is not for interacting
 * with the game data as its used.
 *
 * @author qrq1356
 */
public class DatabaseManager {

    private static final Logger log
            = Logger.getLogger(DatabaseManager.class.getName());
    private static final String DB_URL = "jdbc:derby:Chess_v1;create=true";
    private static final String USERS_TABLE = "USERS", GAMES_TABLE = "GAMES", MOVES_TABLE = "MOVES";
    private Connection connection;

    /**
     * Establishes a connection to the database.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            log.info("Successfully connected to the database.");
        } catch (SQLException ex) {
            log.severe("DB CONNECT: Error connecting to the database."
                    + "Error message: " + ex.getMessage());
        }
    }

    /**
     * creates the users table. if the table already exists inform log and
     * return.
     */
    public void createUsersTable() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, USERS_TABLE, null);
            if (result.next()) {
                log.fine("USERS TABLE ATTEMPTED RECREATION.");
                return;
            }
            result.close();
            String sql = "CREATE TABLE " + USERS_TABLE
                    + "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "wins INT DEFAULT 0,"
                    + "losses INT DEFAULT 0)";
            connection.createStatement().executeUpdate(sql);
            log.finer("USERS TABLE CREATED SUCCCESSFULLY");
        } catch (SQLException ex) {
            log.severe("CREATE TABLE USER: " + ex.getMessage());
        }
    }

    /**
     * creates the games table. if the table already exists inform log and
     * return.
     */
    public void createGamesTable() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, GAMES_TABLE, null);
            if (result.next()) {
                log.fine(GAMES_TABLE + " Table already exists");
            }
            result.close();
            String sql = "CREATE TABLE " + GAMES_TABLE
                    + "(ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "Player1 INT REFERENCES " + USERS_TABLE + "(ID),"
                    + "Player2 INT REFERENCES " + USERS_TABLE + "(ID),"
                    + "Status BOOLEAN)";
            connection.createStatement().executeUpdate(sql);
            log.finer(GAMES_TABLE + " Table created successfully");
        } catch (SQLException ex) {
            log.severe("CREATE TABLE GAMES: " + ex.getMessage());
        }
    }

    /**
     * creates the moves table. if the table already exists inform log and
     * return.
     */
    public void createMovesTable() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, MOVES_TABLE, null);
            if (result.next()) {
                log.fine(MOVES_TABLE + " Table already exists");
            }
            result.close();
            String sql = "CREATE TABLE " + MOVES_TABLE
                    + "(ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                    + "GameID INT REFERENCES " + GAMES_TABLE + "(ID),"
                    + "PlayerID INT REFERENCES " + USERS_TABLE + "(ID),"
                    + "FromSquare VARCHAR(2),"
                    + "ToSquare VARCHAR(2))";
            connection.createStatement().executeUpdate(sql);
            log.finer(MOVES_TABLE + " Table created successfully");
        } catch (SQLException ex) {
            log.severe("CREATE TABLE MOVES: " + ex.getMessage());
        }
    }

    /**
     * Adds a user with the given username.
     *
     * @param username the username of the user to be added
     * @return 0 on success 1 if the given username is invalid (inform with
     * finest log) 2 if a user with the given username already exists (inform
     * with finest log) 3 on miscellaneous error (inform with severe log)
     */
    public int addUser(String username) {
        if (!username.matches("[a-zA-Z0-9]+") || username.length() > 50) {
            log.finest("CREATE USER: invalid name condition.");
            return 2;
        }
        try {
            PreparedStatement exists = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + USERS_TABLE + " WHERE (username = ?)"
            );
            exists.setString(1, username);
            ResultSet resultSet = exists.executeQuery();
            if (resultSet.next()) {
                log.finest("CREATE USER: name taken condition.");
                return 2;
            }
            PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO " + USERS_TABLE + " (username) VALUES (?)"
            );
            insert.setString(1, username);
            insert.executeUpdate();
            return 0;
        } catch (SQLException ex) {
            log.severe("CREATE USER: " + ex.getMessage());
            return 3;
        }
    }

    /**
     * Increments the win count of a user with the given username.
     *
     * @param username the username of the user to increment the win count of
     * @return 0 on success 1 if the given username is invalid (inform with
     * severe log) 2 if no user exists with the given username (inform with
     * severe log) 3 on miscellaneous error (inform with severe log)
     */
    public int incrementWins(String username) {
        if (!username.matches("[a-zA-Z0-9]+") || username.length() > 50) {
            log.severe("INCREMENTWINS: Invalid username: " + username);
            return 1;
        }
        try {
            if (!checkUserExists(username)) {
                log.severe("INCREMENTWINS: No user found with username: " + username);
                return 2;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE " + USERS_TABLE + " SET wins = wins + 1 WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return 0;
            } else {
                log.severe("INCREMENTWINS: Failed with username: " + username);
                return 3;
            }
        } catch (SQLException ex) {
            log.severe("INCREMENTWINS: SQLException with: " + username
                    + ". Error message: " + ex.getMessage());
            return 3;
        }
    }

    /**
     * Increments the loss count of a user with the given username.
     *
     * @param username the username of the user to increment the loss count of
     * @return 0 on success 1 if the given username is invalid (inform with
     * severe log) 2 if no user exists with the given username (inform with
     * severe log) 3 on miscellaneous error (inform with severe log)
     */
    public int incrementLosses(String username) {
        if (!username.matches("[a-zA-Z0-9]+") || username.length() > 50) {
            log.severe("INCREMENTLOSSES: Invalid username: " + username);
            return 1;
        }
        try {
            if (!checkUserExists(username)) {
                log.severe("INCREMENTLOSSES: No user found with username: " + username);
                return 2;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE " + USERS_TABLE + " SET losses = losses + 1 WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return 0; // Success
            } else {
                log.severe("INCREMENTLOSSES: Failed with username: " + username);
                return 3; // Miscellaneous failure
            }
        } catch (SQLException ex) {
            log.severe("INCREMENTLOSSES: SQLException with: " + username
                    + ". Error message: " + ex.getMessage());
            return 3; // Miscellaneous failure
        }
    }

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    private boolean checkUserExists(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + USERS_TABLE
                    + " WHERE username = ?"
            );
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            log.severe("CHECKUSEREXISTS: Error checking user: " + username
                    + ". Error message: " + ex.getMessage());
        }
        return false;
    }
}
