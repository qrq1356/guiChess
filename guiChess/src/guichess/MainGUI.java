package guichess;
import javax.swing.*;

public class MainGUI{
    private final StartWindow main;
    public MainGUI() {
        try { // attempt to set the theme to match the systems.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println("UI LAF_CLASS: " + ex.getMessage());
        }
        main = new StartWindow();
        main.setVisible(true);
    }
}
