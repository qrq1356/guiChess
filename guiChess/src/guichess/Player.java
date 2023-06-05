package guichess;


public abstract class Player {
    protected boolean isUp;
    
    public int getStartRow() {
        return isUp ? 1 : 6;
    }
    public int getDirection() {
        return isUp ? 1 : -1;
    }
}
