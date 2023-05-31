package guiChess;
import javax.swing.*;

public class MainGUI{
    public JFrame frame;
    public StartPage sp;
    public MainGUI() {
        try { // attempt to set the theme to match the systems.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        frame = new JFrame("Chess Game");
        sp = new StartPage();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(sp);
        frame.pack();
        frame.setVisible(true);
        return;
    }
    public void StartPage() {
        sp.setVisible(true);
    }
}
