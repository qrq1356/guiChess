package guiChess;

public class init {
    private static final MainGUI gui = new MainGUI();
    public static void main(String[] args) {     
        // start onboarding GUI, should load a user before we progress.
        gui.StartPage();
        // if a user is loaded, move onto selecting a game the user owns or making a new one
    }
}
