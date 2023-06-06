package guiChess.UI;

import javax.swing.*;
import guiChess.GameEngine;

public class ChessPanel extends JPanel implements GameObserver {
    private GameEngine gameEngine;
    public ChessPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        gameEngine.registerObserver(this);
        //todo rest of layout and components
    }
    @Override
    public void onGameStateChange(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        //todo update the UI
    }
}
