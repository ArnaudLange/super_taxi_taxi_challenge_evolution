package prototypeRoute;

/**
 * Created by Cokral on 17/05/2016.
 */
public class Test {

    public static void main(String[] args) {

        Case route1 = new Route(15,15,Direction.South);
        Case route2 = new Route(15,15,Direction.North);
        Case route3 = new Route(15,15,Direction.East);
        Case route4 = new Route(15,15,Direction.West);


        //TEST UNITAIRES

        //test route 1
        System.out.println("Test route1 : direction = " + route1.getD());
        System.out.println("\tCas South :");
        System.out.println("\t\t" + route1.checkAction(Direction.South));
        System.out.println("\tCas North :");
        System.out.println("\t\t" + route1.checkAction(Direction.North));
        System.out.println("\tCas West :");
        System.out.println("\t\t" + route1.checkAction(Direction.West));
        System.out.println("\tCas East :");
        System.out.println("\t\t" + route1.checkAction(Direction.East));
        //test route 2
        System.out.println("Test route2 : direction = " + route2.getD());
        System.out.println("\tCas South :");
        System.out.println("\t\t" + route2.checkAction(Direction.South));
        System.out.println("\tCas North :");
        System.out.println("\t\t" + route2.checkAction(Direction.North));
        System.out.println("\tCas West :");
        System.out.println("\t\t" + route2.checkAction(Direction.West));
        System.out.println("\tCas East :");
        System.out.println("\t\t" + route2.checkAction(Direction.East));
        //test route 3
        System.out.println("Test route3 : direction = " + route3.getD());
        System.out.println("\tCas South :");
        System.out.println("\t\t" + route3.checkAction(Direction.South));
        System.out.println("\tCas North :");
        System.out.println("\t\t" + route3.checkAction(Direction.North));
        System.out.println("\tCas West :");
        System.out.println("\t\t" + route3.checkAction(Direction.West));
        System.out.println("\tCas East :");
        System.out.println("\t\t" + route3.checkAction(Direction.East));
        //test route 4
        System.out.println("Test route4 : direction = " + route4.getD());
        System.out.println("\tCas South :");
        System.out.println("\t\t" + route4.checkAction(Direction.South));
        System.out.println("\tCas North :");
        System.out.println("\t\t" + route4.checkAction(Direction.North));
        System.out.println("\tCas West :");
        System.out.println("\t\t" + route4.checkAction(Direction.West));
        System.out.println("\tCas East :");
        System.out.println("\t\t" + route4.checkAction(Direction.East));
    }

}
