package guiChess;
import javax.swing.*;

public class MainGUI{
    private StartWindow main;
    public MainGUI() {
        try { // attempt to set the theme to match the systems.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        main = new StartWindow();
        return;
    }
    public void StartPage() {
        main.setVisible(true);
    }
}
