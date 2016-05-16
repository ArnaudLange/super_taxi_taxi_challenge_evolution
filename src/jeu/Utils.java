package jeu;

import java.util.Random;

/**
 * Created by Cokral on 03/05/2016.
 */
public class Utils {

    public static int randInt(int max) {
        Random generator = new Random();
        int i = generator.nextInt(max) + 1;

        return i;
    }
}
