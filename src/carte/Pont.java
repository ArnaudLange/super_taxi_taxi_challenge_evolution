package carte;

import static jeu.Utils.randInt;

/**
 * Created by jimmy on 02/05/16.
 */
public class Pont extends Case{

    public Pont(){
        super(0,0,Direction.North);
    }

    public Pont(int posx, int posy, Direction d) {
        super(posx, posy, d);
    }

    @Override
    public boolean checkAction() {
        System.out.println("La case vérifie si le déplacement dans la direction donnée est possible.");
        int rand = randInt(2);
        if(rand == 1){
            System.out.println("Action possible.");
            return true;
        }
        else if (rand == 2){
            System.out.println("Action impossible.");
            return false;
        }
        return false;
    }
}
