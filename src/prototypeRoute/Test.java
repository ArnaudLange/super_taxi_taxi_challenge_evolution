package prototypeRoute;

/**
 * Created by Cokral on 17/05/2016.
 */
public class Test {

    public static void main(String[] args) {

        Case route1 = new Route(15,15);
        route1.addDirection(Direction.North);
        route1.addDirection(Direction.South);
        Case route2 = new Route(15,15);
        route2.addDirection(Direction.East);
        route2.addDirection(Direction.West);


        //TEST UNITAIRES

        //test route 1
        Joueur j1 = new Joueur(3,Direction.North,12);
        Joueur j2 = new Joueur(3,Direction.South,12);
        Joueur j3 = new Joueur(3,Direction.East,12);
        Joueur j4 = new Joueur(3,Direction.West,12);


        System.out.println("Test route1 : direction = " + route1.getListeDirection());
        System.out.println("\tCas North :");
        System.out.println("\t\t" + route1.checkAction(j1));
        System.out.println("Nombre points : " + j1.getNbPoints());
        System.out.println("\tCas South :");
        System.out.println("\t\t" + route1.checkAction(j2));
        System.out.println("Nombre points : " + j2.getNbPoints());
        System.out.println("\tCas West :");
        System.out.println("\t\t" + route1.checkAction(j3));
        System.out.println("Nombre points : " + j3.getNbPoints());
        System.out.println("\tCas East :");
        System.out.println("\t\t" + route1.checkAction(j4));
        System.out.println("Nombre points : " + j4.getNbPoints());
        //test route 2
        System.out.println("Test route2 : direction = " + route2.getListeDirection());
        System.out.println("\tCas North :");
        System.out.println("\t\t" + route2.checkAction(j1));
        System.out.println("Nombre points : " + j1.getNbPoints());
        System.out.println("\tCas South :");
        System.out.println("\t\t" + route2.checkAction(j2));
        System.out.println("Nombre points : " + j2.getNbPoints());
        System.out.println("\tCas West :");
        System.out.println("\t\t" + route2.checkAction(j3));
        System.out.println("Nombre points : " + j3.getNbPoints());
        System.out.println("\tCas East :");
        System.out.println("\t\t" + route2.checkAction(j4));
        System.out.println("Nombre points : " + j4.getNbPoints());
    }
}
