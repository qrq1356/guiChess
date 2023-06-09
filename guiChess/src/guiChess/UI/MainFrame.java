package guiChess.UI;

import guiChess.SessionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    SessionManager sessionManager;
    CardLayout cl = new CardLayout();
    JPanel cards = new JPanel(cl);

    public MainFrame(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        cards.add(new LoginPanel(sessionManager), "loginPanel");
        this.add(cards);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showUser() {
        cards.add(new UserPanel(sessionManager), "userPanel");
        cl.show(cards, "userPanel");
    }

    public void showChess() {
        cards.add(new ChessPanel(sessionManager), "chessPanel");
        cl.show(cards, "chessPanel");
    }

}
