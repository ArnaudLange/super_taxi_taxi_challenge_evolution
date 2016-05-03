package carte;

import java.util.List;

/**
 * Created by jimmy on 02/05/16.
 */
public class Route extends Case
{
    private List<PointCardinal> directions;

    public Route(int posx, int posy, List<PointCardinal> directions)
    {
        this.posx = posx;
        this.posy = posy;
        this.directions = directions;
    }

    public List<PointCardinal> getDirections()
    {
        return this.directions;
    }
}
