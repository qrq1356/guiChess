package guiChess;
import guiChess.UI.MainFrame;

import java.util.logging.Logger;
import javax.swing.DefaultListModel;
/**
 * manages the state of the running session of guiChess
 * it does not manage individual games, see GameEngine.java
 * it does not manage Data, see DatabaseManager.java
 * it does not manage UI, see UI.FrameManager.java
 * @author qrq1356
 */
public class SessionManager {
    private static final Logger log = Logger.getLogger(SessionManager.class.getName());
    private final DatabaseManager dbm = new DatabaseManager();
    private final GameEngine gameEngine = new GameEngine();
    private final MainFrame mainFrame;
    private String currentUser;
    public SessionManager() {
        // init database
        dbm.connect();
        dbm.createTables();
        log.info("Database connected, tables initialized");
        // start the UI
        this.mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
        log.info("UI shown to user");
        loadBot();
    }

    // game management
    public void createGame() {
        dbm.newGame(currentUser);
    }
    public void loadGame(int id) {
        gameEngine.playGameFromList(dbm.movesForGame(id));
    }

    // user management
    public int createUser(String name) {return dbm.addUser(name);}
    public void loadUser(String name) {
        currentUser = name;
        gameEngine.initUp(new Human(name, true));
    }
    public void loadBot() {gameEngine.initDown(new Bot("Bot", false));}
    // card movement
    public void toLogin() {mainFrame.showLogin();}
    public void toUser() {mainFrame.showUser();}
    public void toChess() {mainFrame.showChess();}
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
}
