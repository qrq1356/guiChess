package guiChess.UI;

import guiChess.SessionManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240),
            FOREGROUND_COLOR = new Color(0, 43, 54),
            BACKGROUND_COLOR_2 = new Color(230, 230, 230),
            PRIMARY_COLOR = new Color(108, 113, 196);

    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24),
            LABEL_FONT = new Font("Arial", Font.PLAIN, 16),
            BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Border SOFT_BEVEL_BORDER = BorderFactory.createSoftBevelBorder(0);

    private final SessionManager sessionManager;
    // dynamic components need higher scope.
    private JList<String> gameList;

    public UserPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }

    private void initializeUI() {
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(SOFT_BEVEL_BORDER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridx = 1;
        c.gridy = 0;
        add(headerPanel, c);
        constructHeaderPanel(headerPanel);

        // Users Games List Panel
        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(SOFT_BEVEL_BORDER);
        c.weighty = 0.9;
        c.gridy = 1;
        add(selectPanel, c);
        constructSelectPanel(selectPanel);

        // Empty space panels on the left and right
        JPanel leftSpacePanel = new JPanel();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        add(leftSpacePanel, c);
        JPanel rightSpacePanel = new JPanel();
        c.gridx = 2;
        add(rightSpacePanel, c);
    }

    private void constructHeaderPanel(JPanel headerPanel) {
        JLabel headerLabel = new JLabel("Join an existing or create a new game");
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        headerLabel.setFont(HEADER_FONT);
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerLabel.setForeground(FOREGROUND_COLOR);
        headerPanel.add(headerLabel, c);
    }

    private void constructSelectPanel(JPanel selectPanel) {
        selectPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // game list
        c.insets = new Insets(10, 10, 10, 10);
        gameList = new JList<>(sessionManager.getGameNames());
        gameList.setFont(LABEL_FONT);
        gameList.setSelectionBackground(PRIMARY_COLOR);
        gameList.setSelectionForeground(BACKGROUND_COLOR);
        gameList.setBackground(BACKGROUND_COLOR_2);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setLayoutOrientation(JList.VERTICAL);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridheight = 3;
        selectPanel.add(new JScrollPane(gameList), c);
        c.insets = new Insets(0, 0, 0, 0);

        // join button
        JButton joinButton = new JButton("Join");
        joinButton.setFont(BUTTON_FONT);
        joinButton.setBackground(PRIMARY_COLOR);
        joinButton.setForeground(BACKGROUND_COLOR);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 0.1;
        c.weightx = 0.1;
        c.gridheight = 1;
        c.gridx = 1;
        joinButton.addActionListener(new JoinButtonListener());
        selectPanel.add(joinButton, c);

        // create button
        JButton createButton = new JButton("Create Game");
        createButton.setFont(BUTTON_FONT);
        createButton.setBackground(PRIMARY_COLOR);
        createButton.setForeground(BACKGROUND_COLOR);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        createButton.addActionListener(new CreateGameButtonListener());
        selectPanel.add(createButton, c);
    }

    private class JoinButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionManager.loadGame(Integer.parseInt(gameList.getSelectedValue().split(",")[0]));
            sessionManager.toChess();
        }
    }

    private class CreateGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionManager.createGame();
            sessionManager.toChess();
        }
    }
}