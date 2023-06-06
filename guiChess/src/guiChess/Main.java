package guiChess;
public class Main {
    // yep this is all it takes to start the program.
    // the rest of the program is managed by the SessionManager
    // this is the only class that should be in the default package
    public static void main(String[] args) {
        SessionManager sessionManager = new SessionManager();
    }
}
