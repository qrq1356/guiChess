package guiChess.UI;

import guiChess.SessionManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private SessionManager sessionManager;
    private JList<String> gameList;
    private JTextField createGameField;

    public UserPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridLayout(3, 1, 0, 20));
        // header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        JLabel headerLabel = new JLabel("pick a game");
        headerPanel.add(headerLabel);
        add(headerPanel);
        // game list
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(1,2,0,0));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        gameList = new JList<>(sessionManager.getGameNames());
        gamePanel.add(new JScrollPane(gameList));
        // join button
        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(new JoinButtonListener());
        gamePanel.add(joinButton);
        add(gamePanel);
        // create
        JPanel createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(1,1,0,0));
        createPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        // create name field
        createGameField = new JTextField(10);
        createPanel.add(createGameField);
        // create button
        JButton createButton = new JButton("Create Game");
        createButton.addActionListener(new CreateGameButtonListener());
        createPanel.add(createButton);
        add(createPanel);
    }
    private class JoinButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String gameName = gameList.getSelectedValue();
            if (gameName != null) {
                //sessionManager.joinGame(gameName);
            }
        }
    }
    private class CreateGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String gameName = createGameField.getText();
            if (gameName != null && !gameName.isEmpty()) {
                //sessionManager.createGame(gameName);
            }
        }
    }
}