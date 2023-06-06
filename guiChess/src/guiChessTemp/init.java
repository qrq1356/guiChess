package guichess;

public class init {
    private static final DatabaseManager udb = new DatabaseManager();
    public static void main(String[] args) {
        udb.connect();
        udb.initTables();
        
        // start onboarding GUI, should load a user before we progress.
        MainGUI gui = new MainGUI();
        // TODO: check for a loaded user
        // if a user is loaded, move onto selecting a game the user owns or making a new one
       
    }
}
