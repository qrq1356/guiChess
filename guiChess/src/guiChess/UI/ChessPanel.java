package guiChess.UI;

import javax.swing.*;

import guiChess.GameEngine;
import guiChess.Move;
import guiChess.Pieces.Piece;
import guiChess.Position;
import guiChess.SessionManager;

import javax.swing.border.Border;
import java.awt.*;

public class ChessPanel extends JPanel implements GameObserver {
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240),
            FOREGROUND_COLOR = new Color(0, 43, 54),
            BACKGROUND_COLOR_2 = new Color(230, 230, 230),
            PRIMARY_COLOR = new Color(108, 113, 196),
            ERROR_COLOR = new Color(220, 50, 47);

    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24),
            LABEL_FONT = new Font("Arial", Font.PLAIN, 16),
            BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Border SOFT_BEVEL_BORDER = BorderFactory.createSoftBevelBorder(0);

    private final SessionManager sessionManager;
    // dynamic components need higher scope.
    private GameEngine gameEngine;
    private JButton[][] buttons;
    private Position selectedPosition;
    private JLabel errorLabel;
    private JLabel turnLabel;
    private JList<String> moveList;
    

    public ChessPanel(SessionManager sessionManager) {
        this.gameEngine = sessionManager.getGameEngine();
        this.sessionManager = sessionManager;
        gameEngine.registerObserver(this);
        initializeUI(gameEngine);
    }

    private void initializeUI(GameEngine gameEngine) {
        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0.6, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(20, 20, 20, 20), 0, 0);
        // colours
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);

        // board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(SOFT_BEVEL_BORDER);
        initButtons(boardPanel, gameEngine);
        add(boardPanel, c);

        // side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBorder(SOFT_BEVEL_BORDER);
        c.weightx = 0.4;
        c.gridx = 1;
        initSidePanel(sidePanel, gameEngine);
        add(sidePanel, c);

    }
    private void initSidePanel(JPanel owner, GameEngine gameEngine) {
        owner.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        // error label
        errorLabel = new JLabel();
        errorLabel.setFont(LABEL_FONT);
        errorLabel.setForeground(ERROR_COLOR);
        owner.add(errorLabel, c);

        // game info panel
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        // turn label
        turnLabel = new JLabel();
        turnLabel.setFont(LABEL_FONT);
        turnLabel.setForeground(PRIMARY_COLOR);
        gameInfoPanel.add(turnLabel, c2);

        // move history list
        moveList = new JList<>();
        moveList.setFont(LABEL_FONT);
        moveList.setForeground(FOREGROUND_COLOR);
        moveList.setBackground(BACKGROUND_COLOR);
        moveList.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(moveList);
        scrollPane.setBorder(SOFT_BEVEL_BORDER);
        c2.gridy = 1;
        gameInfoPanel.add(scrollPane, c2);
        // finish
        c.gridy = 1;
        owner.add(gameInfoPanel, c);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        // save button
        JButton saveButton = new JButton("Save");
        saveButton.setFont(BUTTON_FONT);
        saveButton.setForeground(FOREGROUND_COLOR);
        saveButton.setBackground(BACKGROUND_COLOR);
        saveButton.addActionListener(e -> onSaveButtonPress());
        buttonPanel.add(saveButton, c3);

        // close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(BUTTON_FONT);
        closeButton.setForeground(FOREGROUND_COLOR);
        closeButton.setBackground(BACKGROUND_COLOR);
        //closeButton.addActionListener(e -> onCloseButtonPress());
        c3.gridy = 1;
        buttonPanel.add(closeButton, c3);
        c.gridy = 2;
        owner.add(buttonPanel, c);
    }

    private void onSaveButtonPress() {
        sessionManager.saveGame();
    }
    private void initButtons(JPanel parent, GameEngine gameEngine) {
        parent.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0);
        buttons = new JButton[8][8];
        for (int i = 7; i >= 0; i--) {  // start from 7
            for (int j = 0; j < 8; j++) {
                c.gridx = j;
                c.gridy = 7 - i;
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                buttons[i][j] = button;
                button.setBackground((i + j) % 2 == 0 ? BACKGROUND_COLOR : BACKGROUND_COLOR_2);
                Position pos = new Position(j, i);
                button.addActionListener(e -> onPiecePress(pos));
                parent.add(button, c);
            }
        }
        updateButtons(gameEngine);
    }

    public void updateButtons(GameEngine gameEngine) {
        for (int i = 7; i >= 0; i--) {  // start from 7
            for (int j = 0; j < 8; j++) {
                Piece piece = gameEngine.getPieceAt(new Position(i, j));
                if (piece != null) {
                    String inp = piece.toString();
                    int firstDotIndex = inp.indexOf('.');
                    int secondDotIndex = inp.indexOf('.', firstDotIndex + 1);
                    int atIndex = inp.indexOf('@');
                    if (piece.getOwner().getDirection() == 1) {
                        buttons[i][j].setForeground(PRIMARY_COLOR);
                    } else {
                        buttons[i][j].setForeground(ERROR_COLOR);
                    }
                    String result = inp.substring(secondDotIndex + 1, atIndex);
                    buttons[i][j].setText(result);
                } else {
                    buttons[i][j].setText("");
                }
            }
        }
    }


    @Override
    public void onGameStateChange(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        updateButtons(gameEngine);
    }

    public void onPiecePress(Position pos) {
        if (selectedPosition == null) {
            selectedPosition = pos;
            buttons[pos.getCol()][pos.getRow()].setBackground(Color.RED);
        } else {
            buttons[selectedPosition.getCol()][selectedPosition.getRow()].setBackground((selectedPosition.getCol() + selectedPosition.getRow()) % 2 == 0 ? BACKGROUND_COLOR : BACKGROUND_COLOR_2);
            gameEngine.play(new Move(new Position(selectedPosition.getCol(), selectedPosition.getRow()), new Position(pos.getCol(), pos.getRow())));
            selectedPosition = null;
        }
    }

}
