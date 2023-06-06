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
    private DatabaseManager dbm = new DatabaseManager();
    private GameEngine gameEngine = new GameEngine();
    private MainFrame mainFrame;
    public SessionManager() {
        // init database
        dbm.connect();
        dbm.createTables();
        log.info("Database connected, tables initalized");
        // start the UI
        this.mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
        log.info("UI shown to user");
    }
    public DefaultListModel<String> getUserNames() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String name : dbm.getUserNames()) {
            listModel.addElement(name);
        }
        return listModel;
    }
    // create and load a user with the given name
    public void createUser(String name) {
        dbm.addUser(name);
    }
    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
