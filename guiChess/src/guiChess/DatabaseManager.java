package guiChess;

// format transformation.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// logging
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
// database interface


/**
 * This class manages interacting with the database,
 * it is not for interacting with the game data as its used.
 * see SessionManager for that.
 *
 * @author qrq1356
 */
public class DatabaseManager {
    private static final Logger log = Logger.getLogger(DatabaseManager.class.getName());
    // READ ME. before you change the DB URL, have you ensured local permissions and state?
    // remember. netbeans will happily mangle permissions without warning...
    // do not ever set this value to an existing directory relative to the project root.
    private static final String DB_URL = "jdbc:derby:Chess_v3;create=true";
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
        log.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        // Add the console handler to the logger
        log.addHandler(consoleHandler);
    }

    /**
     * creates the users table. if the table already exists inform fine and return.
     * Table format: ID | Username | Wins | Losses
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
                    + "(UserID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "wins INT DEFAULT 0,"
                    + "losses INT DEFAULT 0)";
            connection.createStatement().executeUpdate(sql);
            log.finer("USERS TABLE CREATED SUCCESSFULLY");
        } catch (SQLException ex) {
            log.severe("CREATE TABLE USER: " + ex.getMessage());
        }
    }

    /**
     * creates the games table. if the table already exists inform fine and return.
     * Table format: ID | Player1 | Player2 | Status | Result
     * Null Players are automatically assigned to a Bot.
     * Status is true is the game is over
     * Result is true if player1 won
     */
    // if every game must be owned by a user, then a foreign key constraint is needed.
    public void createGamesTable() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, GAMES_TABLE, null);
            if (result.next()) {
                log.fine(GAMES_TABLE + " Table already exists");
            }
            result.close();
            String sql = "CREATE TABLE " + GAMES_TABLE
                    + "(GameID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "Player1 INT REFERENCES " + USERS_TABLE + "(UserID),"
                    + "Player2 INT REFERENCES " + USERS_TABLE + "(UserID),"
                    + "Status BOOLEAN,"
                    + "Result BOOLEAN)";
            connection.createStatement().executeUpdate(sql);
            log.finer(GAMES_TABLE + " Table created successfully");
        } catch (SQLException ex) {
            log.severe("CREATE TABLE GAMES: " + ex.getMessage());
        }
    }

    /**
     * creates the moves table. if the table already exists inform fine and return.
     * Table format: ID | GameID | ToSquare | FromSquare
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
                    + "(MoveID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                    + "GameID INT REFERENCES " + GAMES_TABLE + "(GameID),"
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
     * @return 0 on success
     * 1 if the given username is invalid and inform finest
     * 2 if a user with the given username already exists and inform finest
     * 3 on miscellaneous error (inform with severe log)
     */
    public int addUser(String username) {
        if (!username.matches("[a-zA-Z0-9]+") || username.length() > 50) {
            log.finest("CREATE USER: invalid name condition.");
            return 1;
        }
        if (userExists(username)) {
            log.finest("CREATE USER: name taken condition.");
            return 2;
        }
        try {
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
     * @return 0 on success
     * 1 if the given username is invalid (inform with severe log)
     * 2 if no user exists with the given username (inform with severe log)
     * 3 on miscellaneous error (inform with severe log)
     */
    public int incrementWins(String username) {
        if (!username.matches("[a-zA-Z0-9]+") || username.length() > 50) {
            log.severe("incrementWins: Invalid username: " + username);
            return 1;
        }
        try {
            if (!userExists(username)) {
                log.severe("incrementWins: No user found with username: " + username);
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
                log.severe("incrementWins: Failed with username: " + username);
                return 3;
            }
        } catch (SQLException ex) {
            log.severe("incrementWins: SQLException with: " + username
                    + ". Error message: " + ex.getMessage());
            return 3;
        }
    }

    /**
     * Increments the loss count of a user with the given username.
     *
     * @param username the username of the user to increment the loss count of
     * @return 0 on success,
     * 1 if the given username is invalid (inform with severe log),
     * 2 if no user exists with the given username (inform with severe log),
     * 3 on miscellaneous error (inform with severe log)
     */
    public int incrementLosses(String username) {
        if (!username.matches("[a-zA-Z0-9]+") || username.length() > 50) {
            log.severe("incrementLosses: Invalid username: " + username);
            return 1;
        }
        try {
            if (!userExists(username)) {
                log.severe("incrementLosses: No user found with username: " + username);
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
                log.severe("incrementLosses: Failed with username: " + username);
                return 3; // Miscellaneous failure
            }
        } catch (SQLException ex) {
            log.severe("incrementLosses: SQLException with: " + username
                    + ". Error message: " + ex.getMessage());
            return 3; // Miscellaneous failure
        }
    }

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username of the user to check
     * @return true if a user with the given username exists, false otherwise
     */
    private boolean userExists(String username) {
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
            log.severe("userExists: Error checking user: " + username
                    + ". Error message: " + ex.getMessage());
        }
        return false;
    }
    public int getWins(String player) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT wins FROM " + USERS_TABLE + " WHERE username = ?"
            );
            statement.setString(1, player);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("wins");
            }
        } catch (SQLException ex) {
            log.severe("getWins: Error getting wins for user: " + player
                    + ". Error message: " + ex.getMessage());
        }
        return -1;
    }
    /**
     * Adds a game entry to the database with a new game ID
     * with player1 as a given Users name string
     * it should account for the input being a string and the ID needing to be set to a user entry
     */
    public int newGame(String player1) {
        try {
            // Find Player1's UserID based on the provided username
            PreparedStatement findPlayer1 = connection.prepareStatement(
                    "SELECT UserID FROM " + USERS_TABLE + " WHERE username = ?"
            );
            findPlayer1.setString(1, player1);
            ResultSet player1Result = findPlayer1.executeQuery();

            // Make sure Player1 exists in the database
            if (!player1Result.next()) {
                log.severe("newGame: " + "GIVEN INVALID PLAYER");
                throw new IllegalArgumentException("Invalid player: " + player1);
            }

            int player1Id = player1Result.getInt("UserID");

            // Insert a new game record with Player1's ID
            PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO " + GAMES_TABLE + " (Player1, Status, Result) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS  // Tell it to return the generated keys
            );
            insert.setInt(1, player1Id);
            insert.setBoolean(2, false);
            insert.setBoolean(3, false);
            insert.executeUpdate();

            // Get the generated game ID
            ResultSet generatedKeys = insert.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);  // The first column in the ResultSet contains the generated ID
            } else {
                log.severe("newGame: Unable to get the game ID.");
                throw new SQLException("Unable to retrieve the game ID.");
            }

        } catch (SQLException ex) {
            log.severe("newGame: " + ex.getMessage());
            return 3; // or any other suitable error code
        }
    }



    /**
     * Constructs a list of moves for the given game entry.
     *
     * @param gameID the ID of the game to retrieve the moves for
     * @return a list of moves
     */
    public List<Move> movesForGame(int gameID) {
        // Retrieve the moves associated with the given game entry from the database
        List<Move> moves = new ArrayList<>();

        // Perform the necessary database query and populate the 'moves' list
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + MOVES_TABLE + " WHERE GameID = ?"
            );
            statement.setInt(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String froms = resultSet.getString("FromSquare");
                if (froms.length() < 2) {
                    log.severe("movesForGame: 'FromSquare' string length is less than 2 for game: " + gameID);
                    continue;
                }
                int f1 = Character.getNumericValue(froms.charAt(0));
                int f2 = Character.getNumericValue(froms.charAt(1));

                String tos = resultSet.getString("ToSquare");
                if (tos.length() < 2) {
                    log.severe("movesForGame: 'ToSquare' string length is less than 2 for game: " + gameID);
                    continue;
                }
                int t1 = Character.getNumericValue(tos.charAt(0));
                int t2 = Character.getNumericValue(tos.charAt(1));

                Position from = new Position(f1, f2);
                Position to = new Position(t1, t2);
                Move move = new Move(from, to);
                moves.add(move);
            }
        } catch (SQLException ex) {
            log.severe("movesForGame: Error loading moves for game: " + gameID
                    + ". Error message: " + ex.getMessage());
        }
        return moves;
    }

    /**
     * Writes a new set of moves to the database for the given game entry.
     *
     * @param gameID the ID of the game entry to associate the moves with
     * @param moves  the moves to write to the database
     */
    public void writeMovesForGame(int gameID, List<Move> moves) {
        // Delete existing moves for the given gameID
        deleteMovesForGame(gameID);
        // Save the moves to the database, associating them with the given game entry
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + MOVES_TABLE + " (GameID, FromSquare, ToSquare) VALUES (?, ?, ?)"
            );
            for (Move move : moves) {
                statement.setInt(1, gameID);
                String from = move.getFrom().getRow() + Integer.toString(move.getFrom().getCol());
                String to = move.getTo().getRow() + Integer.toString(move.getTo().getCol());
                System.out.println("From: " + from + " To: " + to);
                statement.setString(2, from);
                statement.setString(3, to);

                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            log.severe("writeMovesForGame: Error saving moves for game: " + gameID
                    + ". Error message: " + ex.getMessage());
        }
    }

    /**
     * Deletes all moves associated with the given gameID from the database.
     *
     * @param gameID the gameID of the game whose moves should be deleted
     */
    private void deleteMovesForGame(int gameID) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + MOVES_TABLE + " WHERE GameID = ?"
            );
            statement.setInt(1, gameID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            log.severe("deleteMovesForGame: Error deleting moves for game: " + gameID
                    + ". Error message: " + ex.getMessage());
        }
    }

    /**
     * @return a list of all usernames in the database
     */
    public List<String> getUserNames() {
        List<String> usernames = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT username FROM " + USERS_TABLE
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException ex) {
            log.severe("getUserNames: Error getting user names:" + ex.getMessage());
        }
        return usernames;
    }

    /**
     * gets the game names for a given user
     *
     * @param username the user to get the games for
     * @return a list of game names
     */
    public List<String> getGameNames(String username) {
        List<String> games = new ArrayList<>();
        try {
            // Find Player1's UserID based on the provided username
            PreparedStatement findPlayer1 = connection.prepareStatement(
                    "SELECT UserID FROM " + USERS_TABLE + " WHERE username = ?"
            );
            findPlayer1.setString(1, username);
            ResultSet player1Result = findPlayer1.executeQuery();

            if (player1Result.next()) {
                int player1Id = player1Result.getInt("UserID");

                // Retrieve games for Player1's UserID
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT GameID, Status FROM " + GAMES_TABLE + " WHERE Player1 = ?"
                );
                statement.setInt(1, player1Id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    games.add(resultSet.getString("GameID") + ", complete: " + resultSet.getString("Status"));
                }
            }
        } catch (SQLException ex) {
            log.severe("getGameNames: Error getting games for user: " + username
                    + ". Error message: " + ex.getMessage());
        }
        return games;
    }
}
