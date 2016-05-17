package prototypeRoute;

import static prototypeRoute.Direction.*;

/**
 * Created by Cokral on 17/05/2016.
 */
public class Route extends Case{

    public Route(int posX, int posY) {
        super(posX, posY);
    }


    //
    @Override
    public boolean checkAction(Joueur j) {
        if (j.getVitesse() <= this.getVitesseAutorisee()){
            return this.getListeDirection().contains(j.getDirection());
        }
        else{
            if (this.getListeDirection().contains(j.getDirection())){
                j.setNbPoints(j.getNbPoints()-3);
                return true;
            }
            else {
                return false;
            }
        }
    }
}
