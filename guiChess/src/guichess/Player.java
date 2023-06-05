package guichess;


public abstract class Player {
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
