package guiChess.UI;
import guiChess.SessionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CardLayout cl = new CardLayout();
    JPanel cards = new JPanel(cl);
    public MainFrame(SessionManager sessionManager) {
        cards.add(new LoginPanel(sessionManager), "loginPanel");
        cards.add(new UserPanel(sessionManager), "userPanel");
        cards.add(new ChessPanel(sessionManager.getGameEngine()), "chessPanel");
        this.add(cards);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void showLogin() {
        cl.show(cards, "loginPanel");
    }
    public void showUser() {
        cl.show(cards, "userPanel");
    }
    public void showChess() {
        cl.show(cards, "chessPanel");
    }

}
