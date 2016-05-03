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
                            tableau[i][j] = new Route(i,j,false,'N',false);
                            break;
                        case '\n' :
                            j++;
                    }
                    i++;

                }
                buf = new byte[8];
            }


            //On ferme nos flux de données
            fichier.close();
            cartebuf.close();

            carte = new Carte(tableau);


            System.out.print(carte.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

