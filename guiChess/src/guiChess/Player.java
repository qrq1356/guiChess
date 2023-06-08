package guiChess;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return isUp == player.isUp && Objects.equals(name, player.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, isUp);
    }

}
