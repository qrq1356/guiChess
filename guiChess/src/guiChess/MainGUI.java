package guiChess;
import javax.swing.*;

public class MainGUI{
    private SetupWindow setup;
    public MainGUI() {
        try { // attempt to set the theme to match the systems.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setup = new SetupWindow();
        return;
    }
    public void StartPage() {
        setup.setVisible(true);
    }
}
