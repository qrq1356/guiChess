package guiChess.UI;
import guiChess.GameEngine;
public interface GameObserver {
    void onGameStateChange(GameEngine gameEngine);
}
