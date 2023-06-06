package guiChess;
import guiChess.UI.SetupWindow;

import java.util.logging.Logger;
/**
 * manages the state of the running session of guiChess
 * it does not manage individual games, see GameEngine.java
 * it does not manage Data, see DatabaseManager.java
 * it does not manage UI, see UI.FrameManager.java
 * @author qrq1356
 */
public class SessionManager {
    private static final Logger log
            = Logger.getLogger(SessionManager.class.getName());
    private static DatabaseManager dbm = new DatabaseManager();
    private static SetupWindow setupWindow;
    public void main(String[] args) {
        this.setupWindow = new SetupWindow(this);
        // setupWindow handles the front to loading the user.
        setupWindow.setVisible(true);

    }
    // load game from database, by username.

}
