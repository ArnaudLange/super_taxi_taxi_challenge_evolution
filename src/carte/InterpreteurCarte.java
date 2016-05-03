package carte;

import java.io.*;

/**
 * Created by jimmy on 03/05/16.
 */
public class InterpreteurCarte {

    public static void main(String[] args) {

        FileInputStream fichier;
        BufferedInputStream cartebuf;
        Case[][] tableau;
        Carte carte;


        try {
            fichier = new FileInputStream(new File("src/carte/carte001.txt"));
            cartebuf = new BufferedInputStream(new FileInputStream(new File("src/carte/carte001.txt")));
            // avec un buffer : gain de vitesse de lecture conséquent
            tableau = new Case[30][30];

            byte[] buf = new byte[8];
            int i = 0;
            int j = 0;

            while (fichier.read(buf) != -1) {
                for (byte bit : buf) {
                    char c = (char)bit;
                    //System.out.print(c);

                    switch(c){
                        case 'r' :
                            tableau[j][i] = new Route(j,i,false,'N',false);
                            //System.out.println((tableau[i][j].getClass().getName()));
                            break;
                        case 'n' :
                            tableau[j][i] = new CaseNeutre();
                            break;
                        case 't' :
                            tableau[j][i] = new RouteEnT();
                            break;
                        case '\n' :
                            j++;
                            i = -1; // i va s'incrémenter a la sortie du switch
                            // on le met donc à -1 ici pour arriver à i = 0
                            // au début de la prochaine ligne
                            break;
                    }
                    i++;

                }
                buf = new byte[8];
            }


            //On ferme nos flux de données
            fichier.close();
            cartebuf.close();


            carte = new Carte(tableau);
            carte.setLargeur(j+1); // on rajoute 1 pour gérer le cas de départ où le numéro de la ligne
                                    // est à 0 ( au début)
            carte.setLongueur(i-1); // on enlève 1 pour gérer le cas de sortie
                                    // où le nombre de colonnes est incrémenté tout le temps

            System.out.println(carte.getLargeur());
            System.out.println(carte.getLongueur());



            // on parcourt la carte et on regarde si elle a bien été initalisée en regardant chaque case
            for (j = 0; j < carte.getLargeur();j++){
                for (i = 0; i < carte.getLongueur(); i++){
                    System.out.println("["+j+"]["+i+"]=");
                    System.out.println((carte.getTableau()[j][i]).getClass().getName());
                }
            }


            //System.out.print(carte.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

