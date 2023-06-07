package guiChess.UI;

import guiChess.SessionManager;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private SessionManager sessionManager;
    // dynamic components need higher scope.
    private JList<String> gameList;

    public UserPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // border with padding construct
        Border softBevel = BorderFactory.createSoftBevelBorder(0);
        // header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(softBevel);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 1;
        c.gridy = 0;
        add(headerPanel, c);
        constructHeaderPanel(headerPanel);
        // Users Games List Panel
        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(softBevel);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.9;
        c.gridx = 1;
        c.gridy = 1;
        add(selectPanel, c);
        constructSelectPanel(selectPanel);
        // Empty space panels on the left and right
        JPanel leftSpacePanel = new JPanel();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        add(leftSpacePanel, c);
        JPanel rightSpacePanel = new JPanel();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 3;
        add(rightSpacePanel, c);
    }
    private void constructHeaderPanel(JPanel headerPanel) {
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // header label
        JLabel headerLabel = new JLabel("pick a game");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        headerPanel.add(headerLabel, c);
    }
    private void constructSelectPanel(JPanel selectPanel) {
        selectPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // game list
        gameList = new JList<>(sessionManager.getGameNames());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        selectPanel.add(new JScrollPane(gameList), c);
        // join button
        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(new JoinButtonListener());
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 1;
        selectPanel.add(joinButton, c);
        // create button
        JButton createButton = new JButton("Create Game");
        createButton.addActionListener(new CreateGameButtonListener());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 2;
        selectPanel.add(createButton, c);
    }
    private class JoinButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionManager.loadGame((int) gameList.getSelectedValue().charAt(0));
        }
    }
    private class CreateGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionManager.createGame();
        }
    }
}