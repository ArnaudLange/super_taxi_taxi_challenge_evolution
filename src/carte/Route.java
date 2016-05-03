package carte;

/**
 * Created by jimmy on 02/05/16.
 */
public class Route extends Case{

    private char direction; // N S E W point cardinal
    private boolean stop;

    public Route(int posx, int posy, boolean objectif, char direction, boolean stop){
        this.posx = posx;
        this.posy = posy;
        this.objectif = objectif;
        this.direction = direction;
        this.stop = stop;
    }
}

