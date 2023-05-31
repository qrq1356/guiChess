package guiChess;

public class init {
    private static final MainGUI gui = new MainGUI();
    private static final UserDBManager udb = new UserDBManager();
    public static void main(String[] args) {
        udb.connect();
        udb.createTable(); //note: only does something if the table wasnt loaded in the connection above
        
        // start onboarding GUI, should load a user before we progress.
        gui.StartPage();
        // TODO: check for a loaded user
        // if a user is loaded, move onto selecting a game the user owns or making a new one
       
    }
}
