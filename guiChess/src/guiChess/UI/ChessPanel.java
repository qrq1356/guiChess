package guiChess.UI;

import javax.swing.*;

import guiChess.GameEngine;
import guiChess.Move;
import guiChess.Pieces.Piece;
import guiChess.Position;

import javax.swing.border.Border;
import java.awt.*;

public class ChessPanel extends JPanel implements GameObserver {
    private GameEngine gameEngine;
    private JButton[][] buttons;
    private Position selectedPosition;

    public ChessPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        gameEngine.registerObserver(this);
        initializeUI(gameEngine);
    }

    private void initializeUI(GameEngine gameEngine) {
        setBackground(new Color(238, 232, 213));
        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0.5, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(20, 20, 20, 20), 0, 0);
        Border softBevel = BorderFactory.createSoftBevelBorder(0);
        // board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(softBevel);
        initButtons(boardPanel, gameEngine);
        add(boardPanel, c);
        // info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(softBevel);
        c.weightx = 0.2;
        c.gridx = 1;
        add(infoPanel, c);

    }

    private void initButtons(JPanel parent, GameEngine gameEngine) {
        // layout
        parent.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0.6, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
        buttons = new JButton[8][8];
        for (int i = 7; i >= 0; i--) {  // start from 7
            for (int j = 0; j < 8; j++) {
                c.gridx = j;
                c.gridy = 7 - i;
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                buttons[i][j] = button;
                button.setBackground(Color.WHITE);
                button.setForeground(Color.pink);
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
                        buttons[i][j].setForeground(Color.cyan);
                    } else {
                        buttons[i][j].setForeground(Color.pink);
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
            buttons[selectedPosition.getCol()][selectedPosition.getRow()].setBackground(Color.WHITE);
            //print selected position and pos row and col values for debug
            System.out.print("pos1: " + selectedPosition.getRow() + ":" + selectedPosition.getCol());
            System.out.println(" - pos2: " + pos.getRow() + ":" + pos.getCol());
            gameEngine.playMove(new Move(new Position(selectedPosition.getCol(), selectedPosition.getRow()), new Position(pos.getCol(), pos.getRow())));
            selectedPosition = null;
            gameEngine.botMove();
        }
    }

}
