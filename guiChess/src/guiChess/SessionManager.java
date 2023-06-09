package guiChess;

import guiChess.UI.FinishedPanel;
import guiChess.UI.MainFrame;

import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 * manages the state of the running session of guiChess
 * it does not manage individual games, see GameEngine.java
 * it does not manage Data, see DatabaseManager.java
 * it does not manage UI, see UI.FrameManager.java
 *
 * @author qrq1356
 */
public class SessionManager {
    private static final Logger log = Logger.getLogger(SessionManager.class.getName());
    private final DatabaseManager dbm = new DatabaseManager();
    private final GameEngine gameEngine = new GameEngine();
    private final MainFrame mainFrame;
    private int gameID;
    private String currentUser;

    public SessionManager() {
        // init database
        dbm.connect();
        dbm.createUsersTable();
        dbm.createGamesTable();
        dbm.createMovesTable();
        // start the UI
        this.mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    // game management
    public void createGame() {
        gameID = dbm.newGame(currentUser);
        loadBot();
    }

    public void loadGame(int id) {
        gameID = id;
        gameEngine.playGameFromList(dbm.movesForGame(id));
        loadBot();
    }

    public void saveGame() {
        dbm.writeMovesForGame(gameID, gameEngine.getMoves());
    }
    // user management
    public int createUser(String name) {
        return dbm.addUser(name);
    }

    public void loadUser(String name) {
        currentUser = name;
        gameEngine.initUp(new Human(name, true));
    }

    public void loadBot() {
        gameEngine.initDown(new Bot("Bot", false));
    }

    // card movement
    public void toUser() {
        mainFrame.showUser();
    }
    public void toChess() {
        mainFrame.showChess();
    }

    // getters
    public GameEngine getGameEngine() {
        return gameEngine;
    }
    public DefaultListModel<String> getGameNames() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String name : dbm.getGameNames(currentUser)) {
            listModel.addElement(name);
        }
        return listModel;
    }
    public DefaultListModel<String> getUserNames() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String name : dbm.getUserNames()) {
            listModel.addElement(name);
        }
        return listModel;
    }
    public void CheckGameState() {
        if(gameEngine.isCheckmate(gameEngine.getUp())) {
            dbm.incrementLosses(gameEngine.getUp().getName());
        } else if(gameEngine.isStalemate(gameEngine.getUp()) || gameEngine.isStalemate(gameEngine.getDown())) {
            dbm.incrementLosses(gameEngine.getUp().getName());
        } else if(gameEngine.isCheckmate(gameEngine.getDown())) {
            dbm.incrementWins(gameEngine.getUp().getName());
        } else {
            return;
        }
        mainFrame.showFinished();
    }

    public String getCurrentUser() {
        return currentUser;
    }
    public int getUserWins() {
        return dbm.getWins(currentUser);
    }
    public String getGameResult() {
        if(gameEngine.isCheckmate(gameEngine.getUp())) {
            dbm.incrementLosses(gameEngine.getUp().getName());
            return "Checkmate";
        } else if(gameEngine.isStalemate(gameEngine.getUp()) || gameEngine.isStalemate(gameEngine.getDown())) {
            dbm.incrementLosses(gameEngine.getUp().getName());
            return "Stalemate";
        } else if(gameEngine.isCheckmate(gameEngine.getDown())) {
            dbm.incrementWins(gameEngine.getUp().getName());
            return "win!";
        }
        return "error";
    }
}
