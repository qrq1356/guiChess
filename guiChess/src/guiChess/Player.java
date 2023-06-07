package guiChess;

public abstract class Player {
    protected String name;
    protected boolean isUp;
    public int getPawnRow() {
        return isUp ? 1 : 6;
    }
    public int getBackRow() {
        return isUp ? 0 : 7;
    }
    public int getDirection() {
        return isUp ? 1 : -1;
    }
}
