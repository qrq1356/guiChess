package guiChess.UI;

import guiChess.SessionManager;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private SessionManager sessionManager;
    private JList<String> userList;
    public LoginPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
        //todo rest of layout and components
    }
    private void initializeUI () {
        setLayout(new BorderLayout());
        JLabel headerText = new JLabel("Welcome to Chess");
        headerText.setFont(new Font("Arial", Font.BOLD, 24));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerText, BorderLayout.NORTH);

        userList = new JList<>(sessionManager.getUserNames());
        add(new JScrollPane(userList), BorderLayout.WEST);
    }
}
