package prototypeRoute;

/**
 * Created by Cokral on 17/05/2016.
 */
public abstract class Case {

    private int posX;
    private int posY;
    private Direction d;

    public Case(int posX, int posY, Direction d) {
        this.posX = posX;
        this.posY = posY;
        this.d = d;
    }

    public abstract boolean checkAction(Direction d);

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Direction getD() {
        return d;
    }
}
