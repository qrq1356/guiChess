package guiChess.UI;

import guiChess.SessionManager;
import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private SessionManager sessionManager;
    private JList<String> gameList;

    public UserPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridBagLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        JLabel headerLabel = new JLabel("seects");
        headerPanel.add(headerLabel);
        add(headerPanel);
    }
}