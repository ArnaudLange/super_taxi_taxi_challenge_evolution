package carte;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.FILE;

/**
 * Created by jimmy on 03/05/16.
 */
public class InterpreteurCarte {

    public static Carte Interpreter(File fichierCarte) {

        FileInputStream fichier;
        BufferedInputStream cartebuf;
        Case[][] tableau;
        Carte carte;


        try {
            fichier = new FileInputStream(new File(String.valueOf(fichierCarte)));
            cartebuf = new BufferedInputStream(new FileInputStream(new File(String.valueOf(fichierCarte))));
            // avec un buffer : gain de vitesse de lecture conséquent
            tableau = new Case[30][30];

            byte[] buf = new byte[8];
            int i = 0;
            int j = 0;

            while (fichier.read(buf) != -1) {
                for (byte bit : buf) {
                    char c = (char)bit;



                    switch(c) {
                        case 'r':
                            tableau[i][j] = new Route(i, j, new ArrayList<PointCardinal>());

                            if (i > 0)
                                if (tableau[i - 1][j] instanceof Route) {
                                    ((Route) tableau[i][j]).getDirections().add(PointCardinal.NORTH);
                                    ((Route) tableau[i - 1][j]).getDirections().add(PointCardinal.SOUTH);
                                }

                            if (j > 0)
                                if (tableau[i][j - 1] instanceof Route) {
                                    ((Route) tableau[i][j]).getDirections().add(PointCardinal.WEST);
                                    ((Route) tableau[i][j - 1]).getDirections().add(PointCardinal.EAST);
                                }
                            j++;
                            break;

                        case 'n':
                            tableau[i][j] = new CaseNeutre();
                            j++;
                            break;

                        case '\n':
                            i++;
                            j = 0;
                            break;
                    }
                }
                buf = new byte[8];
            }

            //On ferme nos flux de données
            fichier.close();
            cartebuf.close();

            carte = new Carte(i+1,j);
            carte.setTableau(tableau);

            return carte;


            /*
            System.out.println(carte.getLargeur());
            System.out.println(carte.getLongueur());


            // on parcourt la carte et on regarde si elle a bien été initalisée en regardant chaque case
            for (i = 0; i < carte.getLargeur(); i++)
                for (j = 0; j < carte.getLongueur(); j++)
                {
                    System.out.print("\n[" + j + "][" + i + "]=");
                    System.out.println((carte.getTableau()[i][j]).getClass().getName());
                }

            int sommeChemin = 0;
            int nord = 7;
            int sud = 19;
            int est = 37;
            int ouest = 53;

            for (i = 0; i < carte.getLargeur(); i++)
            {
                for (j = 0; j < carte.getLongueur(); j++)
                {
                    if (carte.getTableau()[i][j] instanceof Route) {
                        if (((Route) carte.getTableau()[i][j]).getDirections().contains(PointCardinal.NORTH))
                            sommeChemin += nord;
                        if (((Route) carte.getTableau()[i][j]).getDirections().contains(PointCardinal.SOUTH))
                            sommeChemin += sud;
                        if (((Route) carte.getTableau()[i][j]).getDirections().contains(PointCardinal.EAST))
                            sommeChemin += est;
                        if (((Route) carte.getTableau()[i][j]).getDirections().contains(PointCardinal.WEST))
                            sommeChemin += ouest;

                        switch (sommeChemin) {
                            case 7:
                                System.out.print("│");
                                break;
                            case 19:
                                System.out.print("│");
                                break;
                            case 37:
                                System.out.print("─");
                                break;
                            case 53:
                                System.out.print("─");
                                break;
                            case 26:
                                System.out.print("│");
                                break;
                            case 44:
                                System.out.print("└");
                                break;
                            case 60:
                                System.out.print("┘");
                                break;
                            case 56:
                                System.out.print("┌");
                                break;
                            case 72:
                                System.out.print("┐");
                                break;
                            case 90:
                                System.out.print("─");
                                break;
                            case 63:
                                System.out.print("├");
                                break;
                            case 79:
                                System.out.print("┤");
                                break;
                            case 97:
                                System.out.print("┴");
                                break;
                            case 109:
                                System.out.print("┬");
                                break;
                            case 116:
                                System.out.print("┼");
                                break;
                        }
                        sommeChemin = 0;
                    } else
                        System.out.print(" ");
                }
                System.out.println("");
            }

            // ─ │   └ ┐ ┘ ┌   ┤ ┴ ┬ ├    ┼
           */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Vector trouverPositionDepart(Carte carte)
    {
        Carte carteDepart = new Carte();
        //int[] posX = new int[30];
        //int[] posY = new int[30];
        Vector positionDepart = new Vector(); // contient les couples de positions possible
        for (int i = 0; i<carte.getLongueur()-1;i++){
            for (int j = 0; j<carte.getLargeur()-1;i++){
                if (carte.getTableau()[i][j] instanceof Route){
                    int[] position = new int[2];
                    positionDepart.addElement(position); //on replit la liste des positions possibles
                }
            }
        }
        return positionDepart;
    }
    public static int[] choisirPositionDepart(Vector positionDepart){
        int indiceAuHasard = (int) (Math.random() * (positionDepart.size() - 1));
        int[] position = (int[]) positionDepart.get(indiceAuHasard);
        positionDepart.remove(indiceAuHasard); // on l'enlève de la liste pour éviter
        // que 2 joueurs se retrouvent au même endroit
        return position;
    }
}