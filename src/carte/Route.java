package carte;

import java.util.List;

/**
 * Created by jimmy on 02/05/16.
 */
public class Route extends Case
{
    private List<PointCardinal> directions;
    private boolean stop;

    public Route(int posx, int posy, boolean objectif, List<PointCardinal> directions, boolean stop)
    {
        this.posx = posx;
        this.posy = posy;
        this.directions = directions;
        this.stop = stop;
    }
}
