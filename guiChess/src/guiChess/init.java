package guiChess;

import javax.swing.SwingUtilities;

public class init {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.StartPage();
        });
    }
}
