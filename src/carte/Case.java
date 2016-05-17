package carte;

/**
 * Created by jimmy on 02/05/16.
 */
public abstract class Case {


    protected int posx ; //position sur l'axe des abcisses
    protected int posy; //position sur l'axe des ordonnées
    protected Direction d;

    public Case(int posx, int posy, Direction d) {
        this.posx = posx;
        this.posy = posy;
        this.d = d;
    }

    public abstract boolean checkAction();
}
