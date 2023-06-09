package guiChess.UI;

import guiChess.SessionManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FinishedPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240),
            FOREGROUND_COLOR = new Color(0, 43, 54),
            PRIMARY_COLOR = new Color(108, 113, 196);

    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24),
            LABEL_FONT = new Font("Arial", Font.PLAIN, 16);
    private static final Border SOFT_BEVEL_BORDER = BorderFactory.createSoftBevelBorder(0);
    private final SessionManager sessionManager;

    public FinishedPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }

    private void initializeUI() {
        // layout
        setLayout(new BorderLayout());
        // colours
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);

        // header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(SOFT_BEVEL_BORDER);
        add(headerPanel, BorderLayout.NORTH);
        constructHeaderPanel(headerPanel);

        // Results Panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setBorder(SOFT_BEVEL_BORDER);
        add(resultsPanel, BorderLayout.CENTER);
        constructResultsPanel(resultsPanel);
    }

    private void constructHeaderPanel(JPanel owner) {
        owner.setLayout(new GridBagLayout());
        owner.setBackground(BACKGROUND_COLOR);
        JLabel headerLabel = new JLabel("Game Over!");
        headerLabel.setFont(HEADER_FONT);
        headerLabel.setForeground(FOREGROUND_COLOR);
        owner.add(headerLabel);
    }

    private void constructResultsPanel(JPanel owner) {
        owner.setLayout(new GridLayout(3,1));
        owner.setBackground(BACKGROUND_COLOR);

        JLabel userLabel = new JLabel("Player: " + getUsername());
        userLabel.setFont(LABEL_FONT);
        userLabel.setForeground(FOREGROUND_COLOR);
        owner.add(userLabel);

        JLabel scoreLabel = new JLabel("Score: " + getScore());
        scoreLabel.setFont(LABEL_FONT);
        scoreLabel.setForeground(FOREGROUND_COLOR);
        owner.add(scoreLabel);

        JLabel resultLabel = new JLabel("Result: " + getResult());
        resultLabel.setFont(LABEL_FONT);
        resultLabel.setForeground(FOREGROUND_COLOR);
        owner.add(resultLabel);
    }

    private String getUsername() {
        //  get session managers current user
        return sessionManager.getCurrentUser();
    }

    private int getScore() {
        return sessionManager.getUserWins();
    }

    private String getResult() {
        return sessionManager.getGameResult();
    }
}
