package prototypeRoute;

import static prototypeRoute.Direction.*;

/**
 * Created by Cokral on 17/05/2016.
 */
public class Route extends Case{

    public Route(int posX, int posY, Direction d) {
        super(posX, posY, d);
    }

    @Override
    public boolean checkAction(Direction d) {

        if ((d == North || d == South) && (this.getD()==North || this.getD()==South)){
            return true;
        }
        else if ((d == West || d == East) && (this.getD()==West || this.getD()==East)){
            return true;
        }
        else{
            return false;
        }
    }
}
