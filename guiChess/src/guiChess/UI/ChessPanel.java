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
    private Position selectedPositon;
    public ChessPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        gameEngine.registerObserver(this);
        initalizeUI(gameEngine);
    }
    private void initalizeUI(GameEngine gameEngine) {
        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(0,0,1,1,0.8,1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10,0,10,0), 0, 0);
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
        parent.setLayout(new GridLayout(8,8));
        buttons = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50,50));
                buttons[i][j] = button;
                Position pos = new Position(j, i);
                button.addActionListener(e -> onPiecePress(pos));
                parent.add(buttons[i][j]);
            }
        }
        updateButtons(gameEngine);
    }
    public void updateButtons(GameEngine gameEngine) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <8; j++) {
                Piece piece = gameEngine.getPieceAt(new Position(i, j));
                if (piece != null) {
                    buttons[i][j].setText(piece.toString());
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
        if(selectedPositon == null) {
            selectedPositon = pos;
            buttons[pos.getCol()][pos.getRow()].setBackground(Color.RED);
        } else {
            buttons[selectedPositon.getCol()][selectedPositon.getRow()].setBackground(Color.WHITE);
            //print selected position and pos row and col values for debug
            System.out.println("selected position: " + selectedPositon.getRow() + " " + selectedPositon.getCol());
            System.out.println("pos: " + pos.getRow() + " " + pos.getCol());
            gameEngine.playMove(new Move(selectedPositon, pos));
            selectedPositon = null;
            gameEngine.botMove();
        }
    }
}
